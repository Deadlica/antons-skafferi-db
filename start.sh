#!/usr/bin/env bash
set -e

DERBY_CP=/opt/derby/lib/derbyrun.jar:/opt/derby/lib/derbyclient.jar:/opt/derby/lib/derbyshared.jar:/opt/derby/lib/derbytools.jar
DATA_DIR=/var/skafferi/derby

mkdir -p "$DATA_DIR"

NEEDS_MIGRATE=0
if [ ! -d "$DATA_DIR/AntonsSkafferiDB" ]; then
  echo "Seeding Derby database from zip..."
  unzip -oq /opt/seed/AntonsSkafferiDB.zip -d "$DATA_DIR"
  rm -f "$DATA_DIR/AntonsSkafferiDB/db.lck"
  NEEDS_MIGRATE=1
fi

# Start Derby in background
java -Dderby.system.home="$DATA_DIR" -jar /opt/derby/lib/derbyrun.jar server start -h 0.0.0.0 -p 1527 &

# Wait for Derby to accept connections
until java -cp "$DERBY_CP" org.apache.derby.tools.ij <<<"CONNECT 'jdbc:derby://localhost:1527/AntonsSkafferiDB;user=APP;password=APP'; EXIT;" >/dev/null 2>&1; do
  sleep 1
done

if [ "$NEEDS_MIGRATE" = "1" ]; then
  echo "Applying schema migration..."
  { echo "CONNECT 'jdbc:derby://localhost:1527/AntonsSkafferiDB;user=APP;password=APP';"
    cat /opt/seed/init-views.sql
    echo "EXIT;"
  } | java -cp "$DERBY_CP" org.apache.derby.tools.ij
fi

# Hand off to Payara's original entrypoint
exec "${SCRIPT_DIR}/entrypoint.sh"
