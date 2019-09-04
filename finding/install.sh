#!/usr/bin/env zsh

source ~/.zshrc

VERSION=1.0.0-SNAPSHOT
PROFILES=" -P163 -Pspring -Papache -Pmaven2"

mvn install:install-file \
        -Dfile=./pom.xml \
        -DgroupId=io.github.yakirchen \
        -DartifactId=finding \
        -Dversion=${VERSION} \
        -Dpackaging=pom

parents=(
)
for p in ${parents[@]}; do
    cd ${p} && \
    mvn install:install-file \
        -Dfile=./pom.xml \
        -DgroupId=io.github.yakirchen \
        -DartifactId=${p} \
        -Dversion=${VERSION} \
        -Dpackaging=pom
    cd -
done;


ms=(
    ktool
)
for m in ${ms[@]}; do
    cd ${m} && \
    mvn clean install -DskipTests -U && \
    cd -
done;
