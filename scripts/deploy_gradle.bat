@echo off
cf create-service p-service-registry standard service-registry
pushd expedition
cf push -p build/libs/expedition-0.0.1-SNAPSHOT.jar
popd
pushd camelboy
cf push -p build/libs/camelboy-0.0.1-SNAPSHOT.jar
popd
echo Done!
