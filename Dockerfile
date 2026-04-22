FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src
COPY . .
RUN mvn -q -DskipTests package

FROM payara/server-full:6.2024.12-jdk17
USER root

# Derby drivers for Payara classpath
ADD https://repo1.maven.org/maven2/org/apache/derby/derbyclient/10.15.2.0/derbyclient-10.15.2.0.jar /opt/payara/appserver/glassfish/domains/domain1/lib/ext/derbyclient.jar
ADD https://repo1.maven.org/maven2/org/apache/derby/derbyshared/10.15.2.0/derbyshared-10.15.2.0.jar /opt/payara/appserver/glassfish/domains/domain1/lib/ext/derbyshared.jar
ADD https://repo1.maven.org/maven2/org/apache/derby/derbytools/10.15.2.0/derbytools-10.15.2.0.jar /opt/payara/appserver/glassfish/domains/domain1/lib/ext/derbytools.jar

# Derby network server install (for the embedded DB)
RUN apt-get update && apt-get install -y --no-install-recommends curl unzip \
 && curl -fL -o /tmp/derby.tar.gz https://archive.apache.org/dist/db/derby/db-derby-10.15.2.0/db-derby-10.15.2.0-bin.tar.gz \
 && mkdir -p /opt/derby \
 && tar -xzf /tmp/derby.tar.gz --strip-components=1 -C /opt/derby \
 && rm /tmp/derby.tar.gz \
 && apt-get remove -y curl && apt-get autoremove -y && rm -rf /var/lib/apt/lists/* \
 && chmod 644 /opt/payara/appserver/glassfish/domains/domain1/lib/ext/derby*.jar \
 && mkdir -p /var/skafferi/event-images /var/skafferi/derby /opt/seed \
 && chown -R payara:payara /var/skafferi /opt/derby

COPY AntonsSkafferiDB.zip /opt/seed/AntonsSkafferiDB.zip
COPY init-views.sql /opt/seed/init-views.sql
COPY start.sh /opt/start.sh
RUN chmod +x /opt/start.sh

COPY --from=build /src/target/*.war /opt/payara/apps/antons-skafferi-db.war
COPY post-boot-commands.asadmin $POSTBOOT_COMMANDS

USER payara
CMD ["/bin/sh", "-c", "/opt/start.sh"]
