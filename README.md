# Multi Tenant Database Approach (multi-tenant-db)

## Objective

Proof-of-Concept with the goal to provide a Multi Tenant Architecture using Hibernate.

## Use Cases Scenarios

1. Create a Custom Connection Pool for each tenant and make them available to hibernate;
2. Users log in the application and depending of their Organization the correct tenant will be loaded and select a suitable connection.
3. Create an operation (ex. some object query) to demonstrate the diferent results since there is a different database/schema for each user. 

## Tech Stack

* Hibernate 4.3.10.FINAL
* Apache DBCP 1.4
* JSF 2.1.21
* Servlet 2.5
* Oracle (Check if you have Oracle Driver in Maven repo, otherwise it will be necessary to intall)

## Build and deploy

1. Create some Oracle databases (different hosts) with some schemas. Be aware to select one of them to be the MAIN database/schema.

2. Clone this project to your local git.

3. Open the file /scripts/database.sql, copy and run the commands marked with MAIN for the MAIN database/schema.

4. Still in the file /scripts/database.sql, for each tenant database/schema copy and run the commands marked with TENANT. 

5. Edit the file /src/main/resources/multi-tenant.properties providing the correct information for each tenant and main to be created (database/schema data).

6. Open a terminal and follow one of these approaches:

 i. Run "mvn clean install" to generate the WAR package to be deployed and copy to the application server;
 
 ii. If you have a tomcat installation, change the tomcat settings for your configuration in /pom.xml and run "clean install tomcat:deploy" (Tomcat must be started);
 
7. Access, http://app-server-host:app-server-port/multi-tenant-db.

8. Try out with multiple users, organizations and tenants.