# API
## Assumptions, a user has an address book, and address book will includes contact entries.
### get address books(list all user accounts)
```
/accounts
```
### get an address book
```
/accounts/id
```
### get all contacts of an address book
```
/accounts/{account_id}/address
```
### get contact entry
```
/accounts/{account_id}/address/{id} or /addresses/{id}
```
### get all contacts of all address books
```
/addresses
```

# Run this program
## It's built on Java1.8 and Spring boot2.05
## 1.Using Maven Command

```
mvn spring-boot:run
```

Tomcat server will be started. 

## 2. Using Executable JAR: Using command prompt, go to the root folder of the project and run the command.
```
mvn clean package
``` 

We will get executable JAR addressbook-0.0.1-SNAPSHOT.jar in target folder. Run this JAR as

```
java -jar target/addressbook-0.0.1-SNAPSHOT.jar
```

Tomcat server will be started. 
