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

deleteSc2saFile() {
    [ -f src/sc2sa.java ] && [ ! -s src/sc2sa.java ] && rm src/sc2sa.java
}

cleanSaFiles() {
    cd test/input
    rm *.sa && echo "all *.sa files in test/input have been erased"
    cd ../..
}

cleanTsFiles() {
    cd test/input
    rm *.ts && echo "all *.ts files in test/input have been erased"
    cd ../..
}

cleanAll() {
    cleanDotClassFiles
    cleanSCFiles
    cleanSaFiles
    cleanTsFiles
    deleteSCDirectory
    deleteSc2saFile
}

testAllFiles() {
    cd src
    for file in $(ls ../test/input|grep -i ".l$"); do
       echo -n "$file: "
        java Compiler ../test/input/$file ../test/input/$file
    done
    cd ..
}

compareAllTrees() {
    cd test/compare_arbres
    [ ! -e compare_arbres_xml ] && make compare_arbres_xml
    for file in $(ls ../sa-ref); do
        ./compare_arbres_xml ../sa-ref/$file ../input/$file
        echo
    done
    cd ../..
}

compareAllTables() {
    for file in $(ls test/ts-ref); do
        echo -en "$file: "
        if [ -z "$(diff test/ts-ref/$file test/input/$file)" ];then
          echo -e "\033[1;32mOk\e[m"
        else
          echo -e "\033[1;31mDifférences trouvées\e[m"
        fi
    done
    cd ../..
}

compile() {
    java -jar sablecc.jar src/grammaireL.sablecc
    cd src
    javac Compiler.java
    cd ..
}

help() {
    echo "Usage: ./process.sh [-c] [-t] [-clsc] [-clgr] [--clean]"
    echo
    echo "  -c:         Creates the grammar with sableCC and compile Compiler"
    echo "  -t:         Tests Compiler on the examples of /test/input"
    echo "  -clsc:      Cleans sc files created after .l files have been tested"
    echo "  -clclass:   Cleans class files"
    echo '  -clgr:      Cleans grammar classes created by sablecc'
    echo "  --clean:    Cleans everything, compiled classes included"
}


[ $# -eq 0 ] && help && exit 0

for arg in $@; do
    case $arg in
        -c)         compile;;
        -t)         testAllFiles;;
        -T)         compareAllTrees;;
        -Ts)        compareAllTables;;
        -clsc)      cleanSCFiles;;
        -clclass)   cleanDotClassFiles;;
        -clgr)      deleteSCDirectory;;
        --clean)    cleanAll;;
        *)          help;;
    esac
done

exit 0
