#!/usr/bin/env zsh

source ~/.zshrc


mvn clean package -DskipTests -P163 -Paliyun

profiles=(
    bioserver
    bioclient
    nioserver
    nioclient
    nettyOIOserver
    nettyNIOserver
    nettyEchoclient
    nettyScheduledclient
)
for p in ${profiles[@]}; do
    mvn package -DskipTests -P163 -Paliyun -P${p}
done;
