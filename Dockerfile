FROM postgres:9.4

COPY ./sql-scripts/ /docker-entrypoint-initdb.d/
