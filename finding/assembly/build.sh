#!/usr/bin/env zsh

profiles=(
  instApp
  instAgent
)

mvn clean

for profile in $profiles[@] ; do
  mvn package -P$profile -DskipTests;
done