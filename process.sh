#!/bin/bash

cleanDotClassFiles() {
    find -name *.class -delete && echo "All *.class files have been erased"
}

cleanSCFiles() {
    cd test/input
    rm *.sc && echo "all *.sc files in test/input have been erased"
    cd ../..
}

deleteSCDirectory() {
    [ ! -d src/sc ] && echo "no sc directory in src" && exit 1
    rm -r src/sc && echo "src/sc package deleted"
}

cleanAll() {
    cleanDotClassFiles
    cleanSCFiles
    deleteSCDirectory
}

testAllFiles() {
    cd src
    for file in $(ls ../test/input); do
        echo -n "$file: "
        java Compiler ../test/input/$file
    done
    cd ..
}

compile() {
    java -jar sablecc.jar src/grammaireL.sablecc
    cd src
    javac Compiler.java
    cd ..
}

help() {
    echo "Usage: ./process.sh [-c | --compile] [-t | --test] [-clsc] [-clgr] [--clean]"
    echo "-c | --compile: Creates the grammar with sableCC and compile Compiler"
    echo "-t | --test: Tests Compiler on the examples of /test/input"
    echo "-clsc: Cleans sc files created after .l files have been tested"
    echo '-clgr: Cleans grammar classes created by sablecc'
    echo "--clean: Cleans everything, compiled classes included"
}


[ $# -eq 0 ] && help && exit 0

for arg in $@; do
    case $arg in
        -c)         compile;;
        --compile)  compile;;
        -t)         testAllFiles;;
        --test)     testAllFiles;;
        -clsc)      cleanSCFiles;;
        -clgr)      deleteSCDirectory;;
        --clean)    cleanAll;;
        *)          help;;
    esac
done

exit 0
