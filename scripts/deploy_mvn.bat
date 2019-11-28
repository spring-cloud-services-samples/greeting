@echo off
echo Creating Service Registry...
cf create-service p.service-registry standard greeter-service-registry > nul
:check
  cf service greeter-service-registry | find "succeeded" > nul
  if errorlevel 1 goto :check
  echo Service Registry created. Pushing applications.
  pushd greeter-messages
  cf push -p target/greeter-messages-0.0.1-SNAPSHOT.jar
  popd
  pushd greeter
  cf push -p target/greeter-0.0.1-SNAPSHOT.jar
  popd
  echo Done!
