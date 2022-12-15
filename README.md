# Project 3


## Getting Started
From the React repository, go to src/remote/e-commerce-api/eCommerceClient.ts. From there, adjust the baseURL key to that it yields a value of 'localhost:8080'. 

```
const eCommerceClient = axios.create({
  withCredentials: true,
  baseURL: 'localhost:8080',
  headers: {
    Accept: "application/json",
    'Content-Type': 'application/json',
  },
});
```

After adjusting your baseURL value, save your work and run the Dockerfile included in the src directory. It should take several minutes to run. 

The development team left the JSON Web Token salt as an environmental variable, and left out a bean for email manipulation (which is used for sending a confirmation after orders). As a solution, one can edit runtime configurations to accommodate a token salt and email information:

```
jwt.secret=guest;
spring.h2.console.enabled=true;
spring.datasource.url=jdbc:h2:mem:memdb;
spring.jpa.defer-datasource-intitialization=true;
spring.mail.host=smtp.gmail.com;
spring.mail.port=465;
spring.mail.properties.mail.smtp.auth=false;
spring.mail.properties.mail.smtp.starttls.enable=false 
```

## H2-Console
When the server is successfully running, you can review the H2 database that is set up automatically when the server runs. Go to [localhost:8080/h2-console] to review the default H2 database. The initial console will request a JDBC URL, which can be reviewed in the Run terminal on line "H2 Console available at '/h2-console'.  Database available at '{url}'. If * *spring.datasource.url* * is defined in the runtime configurations (see above), this is not necessary. The default username and password (or lack of password) is sufficient for login. If * *spring.h2.console.enabled* * is defined as true (see above), the console environment is accessible.  

## Team 2 Runtime Additions
Team 2 added the following files to help during testing:

```
test/java/com.revature.services/PrototypeApplication.java
test/resources/application.properties
test/data.sql
```

**PrototypeApplication.java** runs **ECommerceApplication.java** in main/ with the benefit of pulling configurations from **application.properties** and the database initialization in **data.sql**.  

**application.properties** includes the configuration data included above, so that it is not necessary to include it in runtime configurations in your IDE. 

**data.sql** will run and prepopulate your H2 database as long as * *spring.jpa.defer-datasource-intitialization* * (see above) is declared true. 
