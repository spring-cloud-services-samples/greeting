@echo off
cf create-service p-service-registry standard service-registry
pushd expedition
cf push
popd
pushd camelboy
cf push
popd
echo Done!
