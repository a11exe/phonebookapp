# Database

To run The Backend you need `PostgreSQL ≥9.x` database. "Need" means the database
must be available for the corresponding (apps) services. Currently, it's required
for `authentication-server` and `backoffice-service`;  We plan to add more DBs
(schema-like things in the postrgesql instance) in the future,

Rule: **1 DB for 1 domain service**, so if a domain service needs to store relational 
data somewhere it should store it in its own database inside our PostgreSQL
server instance.

## How to run it
There is a pair of ways:
1. (default) in guest OS: as a docker container
1. in host OS: locally, straight on your laptop\PC 

### Docker container way (default)
1. Build database docker image
    ```shell script
   cd backend/database/docker
   ./build.sh
    ````
1. Run thw image with `./run.sh`   

*Database in docker container starts on port 5432 (DOCKER_DB_PORT variable
in ./run.sh), so if you have something listening locally
(e.g. local postgres service) on port to 5432, the script will ask you to stop it.*


### Local installation on your host OS (not recommended) 

The following steps are for Ubuntu but for other OSs they are roughly the same. E.g. in Windows you
can download and run the installer, after installation completes execute below steps in pgAdmin/psql.

For creating (or recreating again and again) database in your host OS you may find scripts
in `database/localhost`to be useful.
   
#### Ubuntu Linux
Info: https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-18-04

Steps:
1.  Install
    ```bash 
    sudo apt update
    sudo apt install postgresql postgresql-contrib
    ```
1.  Initial config: switch user and set password
    ```bash   
    $ sudo -i -u postgres
    # Set password for "postgres"         
    $ psql 
    \password postgres
    Abc123        
    \q
    # (if the prev. didn't work, use: ALTER USER postgres with password 'Abc123'; )
    # Allow local connections, replace "peer" woth "md5" in the rigth columns
    $ sudo gedit /etc/postgresql/10/main/pg_hba.conf
    $ sudo systemctl restart postgresql
    $ psql -U postgres -W
    ```    
1.  Create DBs & users (manually)
    
    It's just an example of how to create a new db/user. You must create things not showed in the example but the things required 
            for the app to run, see /cloud-config
    *   Create 
        ```bash 
        $ createuser --interactive
        Enter name of role to add: microlms360app
        Shall the new role be a superuser? (y/n) y
        $ createdb microlms360
        ```
    *   Set password `p0stgr@s` like in initial step but for user `microlms360app`
    *   Verify that you able to connect to db `$ psql -U microlms360app -W -d microlms360`.
    Type `\dt` to show tables (obviously before you run auth service it should print nothing).
    *   Enable pgcrypto for crypt() and gen_salt() in queries, in pgsql execute: `CREATE EXTENSION pgcrypto`
    
1.  Create DBs & users (using scripts)
    
    _Check your database credentials or replace it in scripts from `backend/database/localhost`_
    
    To init local database: 
    + Unix run `./initdb.sh` bash file from `backend/database/localhost`
    + Windows run `./initdb.bat` file from `backend/database/localhost`
    
1.  (optional) setup pgAdmin connection         
    ![connection settings](../docs/img/pgAdmin-III_connection_settings.png "connection settings")
