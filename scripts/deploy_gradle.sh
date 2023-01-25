#!/usr/bin/env bash

echo -n "Creating Service Registry..."
{
  cf create-service p.service-registry standard greeter-service-registry
} &>/dev/null
until [ "$(cf service greeter-service-registry | grep -c "succeeded")" -eq 1 ]; do
  echo -n "."
done
echo
echo "Service Registry created. Pushing applications."
pushd greeter-messages && cf push -p build/libs/greeter-messages-0.0.1-SNAPSHOT.jar
popd
pushd greeter && cf push -p build/libs/greeter-0.0.1-SNAPSHOT.jar
popd
echo "" && echo "Done!" && echo ""
