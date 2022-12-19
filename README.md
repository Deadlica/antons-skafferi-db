# Antons Skafferi Database
This is a server application for a restaurant built with the Jakarta framework with a local database connected to a derby pool which mapped to Java persistence units. The application also has a RESTful API which allows communications with the database. This server is used to fulfill requests for a webpage client, as well as 3 different androids apps. It handles the restaurants registrered employees, their shifts, as well as food menus, sales and bookings.

---

## UML Diagram
![](src/main/resources/images/db_uml.png)

---

## ER Diagram
![](src/main/resources/images/antons-skafferi.drawio.png)

---

# Setup
## Requirements
* Jetbrains IntelliJ
* Eclipse GlassFish 6.2.5
* Jakarta EE 9.1 (Platform or Web Profile works)
* Java Development Kit 17.0.5
* Apache Derby drivers (can be installed through IntelliJ)

### Installing GlassFish 6.2.5
All that's required is to extract the files, by default there will be a domain called "domain1". To check that it's working, navigate to the bin folder and try starting the domain with "asadmin"
```bash
cd glassfish6/glassfish/bin
./asadmin start-domain domain1
```
Once glassfish has been successfully launched, go ahead and close it again.
```bash
./asadmin stop-domain domain1
```

### Setup configuration in IntelliJ
1. Open up the project in IntelliJ
2. Open up the "Select Run/Debug Configuration" dropdown menu in the top right corner, click "Edit Configurations...".
3. Click the "+" symbol in the top left corner, select GlassFish Server -> Local.
4. Select GlassFish 6.2.5 as the Application server by browsing to the installation folder.
5. Select domain1 as the Server Domain
6. Add the domains Username and Password. By default, Username is "admin" and there is no password.

Once done it should look something like this.

![](src/main/resources/images/glassfish_configurations.png)

7. Next up, click the "Deployment" tab. Click the "+" then "Artifact..." and lastly "antons-skafferi-db:war"
8. If there are any other errors that shows up, clicking the "Fix" button should resolve it.

### Setup connection pool and connection resource