# Phone book app
Console pnoe book app.
Spring / Hibernate / Postgresql

## Install

    git clone https://github.com/a11exe/phonebookapp
    
## Run

### create postgresql database
    
    database name: phonebookapp
    user: phonebookapp
    password: 123456

### build    
    mvn clean install -DskipTests=true flyway:migrate

### run
    java -jar target/phonebookapp.jar