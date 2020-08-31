echo \*\*\* YOU MUST BE LOGGED WITH DOCKER AND OC-CLI INSIDE TO OPENSHIFT CLUSTER FOR RUN THIS, DO YOU WANT CONTINUE? \*\*\*
read -p "Press any key to continue..."
docker build mysql-simple-site .
docker tag mysql-simple-site 172.30.1.1:5000/test/mysql-simple-site
docker push 172.30.1.1:5000/test/mysql-simple-site
oc new-app --docker-image=172.30.1.1:5000/simple-site/mysql-simplesite-image --name=mysql-simplesite
sleep 5
oc get pods
echo \*\*\* DATABASE SHOULD BE CREATED ON OPENSHIFT CLUSTER \*\*\*