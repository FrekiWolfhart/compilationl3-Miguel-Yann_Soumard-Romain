#!/bin/bash

java -jar sablecc.jar src/grammaireL.sablecc
cd src
javac Compiler.java

for file in $(ls ../test/input); do
    echo -n "$file: "
    java Compiler ../test/input/$file
done

cd ..
cd test/input
rm *.sc

cd ../..
#find -name *.class -delete
