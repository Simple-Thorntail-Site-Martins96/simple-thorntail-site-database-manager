# Prepare the DB image


## Procedure for minishift (version v1.34.2)
This procedure is for docker server old version
### Login into registry with docker
To login into internal registry use:
`docker login -p **PASSWORD** -u unused 172.30.1.1:5000`
change _172.30.1.1:5000_ with the Openshift internal registry address (default should be 172.30.1.1:5000).

The password is generated from console, click on top menù, under symbol (**?**) and select 'About'.
If you use minishift you can use `minishift docker-env`

### Build the image

To build the image run this command inside the "SQL-Scripts" folder
`docker build mysql-simple-site .`

### Tag the version

Tag the version for prepare it to the Openshift registry
`docker tag mysql-simple-site 172.30.1.1:5000/test/mysql-simple-site`

### Push the version to internal registry

Simple run this command:
`docker push 172.30.1.1:5000/test/mysql-simple-site`



## Install on Openshift

Install the database container app with Openshift client CLI
`oc new-app --docker-image=172.30.1.1:5000/simple-site/mysql-simplesite-image --name=mysql-simplesite`