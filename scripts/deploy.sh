cf create-service p-service-registry standard service-registry
sleep 10;
pushd expedition && cf push
popd; sleep 10
pushd camelboy && cf push
popd
echo "" && echo "Done!" && echo ""
