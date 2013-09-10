#!/bin/bash

echo "***25 neurônios***";
java -jar ../kohonen/dist/kohonen.jar config/config2a1.properties  &
java -jar ../kohonen/dist/kohonen.jar config/config2a2.properties  &
java -jar ../kohonen/dist/kohonen.jar config/config2b1.properties  &
java -jar ../kohonen/dist/kohonen.jar config/config2b2.properties  &
java -jar ../kohonen/dist/kohonen.jar config/config2c1.properties  &
java -jar ../kohonen/dist/kohonen.jar config/config2c2.properties  &
exit 1;