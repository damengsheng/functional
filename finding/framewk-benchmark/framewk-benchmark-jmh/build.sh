#!/usr/bin/env zsh

mvn clean compile -DskipTests
mvn -PJMHSample package -DskipTests
mvn -PMethodHandleVSInvocation package -DskipTests
mvn -PStringPlusBenchmark package -DskipTests