@echo off
echo Creating Service Registry...
cf create-service p-service-registry standard service-registry > nul 
:check
  cf service service-registry | find "succeeded" > nul
  if errorlevel 1 goto :check
  echo Service Registry created. Pushing applications.
  pushd message-generation
  cf push -p target/message-generation-0.0.1-SNAPSHOT.jar
  popd
  pushd greeter
  cf push -p target/greeter-0.0.1-SNAPSHOT.jar
  popd
  echo Done!
