#!/bin/bash

echo "***20 neurônios***";
java -jar ../kohonen/dist/kohonen.jar -rt=false config_20/tx03pesoigual.properties igual 
java -jar ../kohonen/dist/kohonen.jar -rt=false config_20/tx03pesorandom.properties random 
java -jar ../kohonen/dist/kohonen.jar -rt=false config_20/tx075pesoigual.properties igual 
java -jar ../kohonen/dist/kohonen.jar -rt=false config_20/tx075pesorandom.properties random 
exit 1;