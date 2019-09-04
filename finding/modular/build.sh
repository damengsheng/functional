#!/usr/bin/env zsh

#source ${HOME}/.zshrc
#set -v -x

jdkver=13
mver=1.0.0

ms=(
    helloworld
)

mainmods=(
    greetings
    hellosocket
)

libmods=(
    helloworld
    socketspi
    fastsocket
)

for mdir in ${ms[@]} ; do
    mkdir -p mods/${mdir}
done
mkdir mlib

/usr/libexec/java_home -v ${jdkver} --exec javac -verbose \
    -d mods \
    --module-path mods \
    --module-source-path src \
    --enable-preview \
    --release ${jdkver} \
    --module-version ${mver} \
    $(find main_java -name "*.java")

for module in ${libmods[@]} ; do
    /usr/libexec/java_home -v ${jdkver} --exec jar --create \
        --file=mlib/${module}@${mver}.jar \
        --module-version=${mver} \
        -C mods/${module} .
done

for module in ${mainmods[@]} ; do
    /usr/libexec/java_home -v ${jdkver} --exec jar --create \
        --file=mlib/${module}@${mver}.jar \
        --main-class=io.github.yakirchen.${module}.Main \
        --module-version=${mver} \
        -C mods/${module} .
done

for module in ${mainmods[@]} ; do
    /usr/libexec/java_home -v ${jdkver} --exec jar \
        --describe-module \
        --file=mlib/${module}@${mver}.jar
done

/usr/libexec/java_home -v ${jdkver} --exec java \
    --enable-preview \
    --module-path mlib \
    -m greetings

/usr/libexec/java_home -v ${jdkver} --exec java \
    --enable-preview \
    --module-path mlib \
    -m hellosocket

#/usr/libexec/java_home -v 11 --exec jlink --module-path $JAVA_HOME/jmods:mlib \
#    --add-modules greetings --output apps/greetingsapp
#
#/usr/libexec/java_home -v 11 --exec jlink --module-path $JAVA_HOME/jmods:mlib \
#    --add-modules hellosocket --output apps/hellosocketapp

#for module in ${ms[@]} ; do
#    javac -verbose \
#       --module-source-path src \
#       --enable-preview \
#       --release 11 \
#       --module-version 1.0.0 \
#       -d mods \
#       src/${module}/*.java
#done

# -d src/${module}/target/classes \

