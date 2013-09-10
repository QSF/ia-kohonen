#!/bin/bash

echo "***36 neurônios***";
java -jar ../kohonen/dist/kohonen.jar config/config2d1.properties
java -jar ../kohonen/dist/kohonen.jar config/config2d2.properties
java -jar ../kohonen/dist/kohonen.jar config/config2e1.properties
java -jar ../kohonen/dist/kohonen.jar config/config2e2.properties
java -jar ../kohonen/dist/kohonen.jar config/config2f1.properties
java -jar ../kohonen/dist/kohonen.jar config/config2f2.properties
exit 1;