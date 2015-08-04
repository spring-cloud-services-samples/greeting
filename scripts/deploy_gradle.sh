cf create-service p-service-registry standard service-registry
sleep 40;
pushd expedition && cf push -p build/libs/expedition-0.0.1-SNAPSHOT.jar
popd; sleep 10
pushd camelboy && cf push -p build/libs/camelboy-0.0.1-SNAPSHOT.jar
popd
echo "" && echo "Done!" && echo ""
