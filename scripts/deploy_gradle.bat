@echo off
cf create-service p-service-registry standard service-registry
pushd message-generation
cf push -p build/libs/message-generation-0.0.1-SNAPSHOT.jar
popd
pushd greeter
cf push -p build/libs/greeter-0.0.1-SNAPSHOT.jar
popd
echo Done!
