FROM postgres:9.5
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB postgres
ENV POSTGRES_MULTIPLE_DATABASES="phonebookapp"
ENV POSTGRES_MULTIPLE_USERS="phonebookapp"
ENV POSTGRES_MULTIPLE_PASSWORDS='123456'

COPY /docker-entrypoint-initdb.d/create-multiple-postgresql-databases.sh /docker-entrypoint-initdb.d/
