cf create-service p-service-registry standard service-registry
sleep 40;
pushd message-generation && cf push -p target/message-generation-0.0.1-SNAPSHOT.jar
popd; sleep 10
pushd greeter && cf push -p target/greeter-0.0.1-SNAPSHOT.jar
popd
echo "" && echo "Done!" && echo ""
