#!/bin/bash

echo "***16 neurônios***";
java -jar ../kohonen/dist/kohonen.jar -rt=false config_16/tx01pesoigual.properties igual &
java -jar ../kohonen/dist/kohonen.jar -rt=false config_16/tx01pesorandom.properties random &
java -jar ../kohonen/dist/kohonen.jar -rt=false config_16/tx03pesoigual.properties igual &
java -jar ../kohonen/dist/kohonen.jar -rt=false config_16/tx03pesorandom.properties random  &
exit 1;