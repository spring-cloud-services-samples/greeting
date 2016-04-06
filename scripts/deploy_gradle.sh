cf create-service p-service-registry standard service-registry
sleep 70;
pushd message-generation && cf push -p build/libs/message-generation-0.0.1-SNAPSHOT.jar
popd; sleep 10
pushd greeter && cf push -p build/libs/greeter-0.0.1-SNAPSHOT.jar
popd
echo "" && echo "Done!" && echo ""
