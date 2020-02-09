#!/bin/bash

checksScDir() {
    if [ ! -d src/sc ]; then
        echo "Need to compile and test first"
        if [ -f process.sh ]; then
            ./process.sh -c -t
        else
            exit 1
        fi
    fi
}

generateSc2saFile() {
    echo -e "import sc.analysis.DepthFirstAdapter;\n"
    echo -e "public class Sc2sa extends DepthFirstAdapter {\n"

    for file in $(ls src/sc/node|grep -i java$|tr -d ".java"); do
        echo -e "\t@Override"
        echo -e "\tpublic void case${file}(${file} node) {\n"
        echo -e "\t}\n"
    done

    echo -e "}"
}

checksScDir
generateSc2saFile > src/sc2sa.java
