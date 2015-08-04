@echo off
cf create-service p-service-registry standard service-registry
pushd expedition
cf push -p target/expedition-0.0.1-SNAPSHOT.jar
popd
pushd camelboy
cf push -p target/camelboy-0.0.1-SNAPSHOT.jar
popd
echo Done!
