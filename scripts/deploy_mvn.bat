@echo off
cf create-service p-service-registry standard service-registry
pushd message-generation
cf push -p target/message-generation-0.0.1-SNAPSHOT.jar
popd
pushd greeter
cf push -p target/greeter-0.0.1-SNAPSHOT.jar
popd
echo Done!
