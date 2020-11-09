echo \*\*\* YOU MUST BE LOGGED WITH DOCKER AND OC-CLI INSIDE TO OPENSHIFT CLUSTER FOR RUN THIS, DO YOU WANT CONTINUE? \*\*\*
read -p "Press any key to continue..."
docker build --tag default-route-openshift-image-registry.apps-crc.testing/simple-site/mysql-simple-site .
docker push default-route-openshift-image-registry.apps-crc.testing/simple-site/mysql-simple-site
oc new-app --docker-image=default-route-openshift-image-registry.apps-crc.testing/simple-site/mysql-simplesite-image --name=mysql-simplesite
sleep 5
oc get pods
echo \*\*\* DATABASE SHOULD BE CREATED ON OPENSHIFT CLUSTER \*\*\*
