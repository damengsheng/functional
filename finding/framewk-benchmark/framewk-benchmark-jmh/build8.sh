#!/usr/bin/env zsh

mvn clean compile -DskipTests -Pjdk8
mvn -PJMHSample package -DskipTests -Pjdk8
mvn -PMethodHandleVSInvocation package -DskipTests -Pjdk8
mvn -PStringPlusBenchmark package -DskipTests -Pjdk8