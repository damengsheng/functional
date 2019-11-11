#!/usr/bin/env zsh

source ~/.zshrc

VERSION=1.0.0-SNAPSHOT
PROFILES=" -Paliyun -Pspring -Papache -Pmaven2 -P163"

mvn install:install-file \
        -Dfile=./pom.xml \
        -DgroupId=io.github.yakirchen \
        -DartifactId=finding \
        -Dversion=${VERSION} \
        -Dpackaging=pom

parents=(
    framewk-benchmark
    framewk-net
    framewk-service
    java-standard
    stream
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
