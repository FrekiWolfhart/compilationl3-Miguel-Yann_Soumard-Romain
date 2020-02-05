#!/bin/bash


testAllFiles() {
    cd src
    for file in $(ls ../test/input); do
        echo -n "$file: "
        java Compiler ../test/input/$file
    done

    cd ..
    cd test/input
    rm *.sc
    cd ../..
}
#find -name *.class -delete

java -jar sablecc.jar src/grammaireL.sablecc
cd src
javac Compiler.java
cd ..

[ $# -eq 0 ] && exit 0

if [ $1 == "-test" ]; then
    echo "Test"
    testAllFiles
fi

exit 0
