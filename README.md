# Phone book app
Console phone book app.
Spring / Hibernate / Postgresql

## Install

    git clone https://github.com/a11exe/phonebookapp
    
## Run

### create postgresql database

    create local database
    
    database name: phonebookapp
    user: phonebookapp
    password: 123456
    
    or you can run database in docker (you must first stop the local postgres on port 5432 if it is running)
    
    cd database/docker
    sh build.sh
    sh run.sh

### build    

    mvn clean install -DskipTests=true flyway:migrate (from phonebook folder)

### run

    java -jar target/phonebookapp.jar (from phonebook folder)