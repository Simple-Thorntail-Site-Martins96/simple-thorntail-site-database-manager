FROM mysql:latest
#Environment credentials
ENV MYSQL_USER=simple_site_user
ENV MYSQL_PASSWORD=Me6aKI6ENoCi
ENV MYSQL_DATABASE=simple_site
ENV MYSQL_ROOT_PASSWORD=admin
EXPOSE 3306
#Info labels
LABEL author="Luca Martinelli"
LABEL name="mysql-simplesite"
#Adding script for init the database
ADD login/ /docker-entrypoint-initdb.d