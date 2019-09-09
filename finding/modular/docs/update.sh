#!/usr/bin/env zsh

versions=(
    "1.8"
    "11"
    "12"
    "13"
    "14"
)

for i in $versions[@]; do
    /usr/libexec/java_home -v ${i} --exec java -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsInitial -version > jvm_options/${i}_print_flags_initial.txt
    /usr/libexec/java_home -v ${i} --exec java -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version > jvm_options/${i}_print_flags_final.txt
done

#opendiff jvm_options/11_print_flags_initial.txt docs/jvm_options/11_print_flags_final.txt
#opendiff jvm_options/8_print_flags_initial.txt docs/jvm_options/8_print_flags_final.txt
#
#opendiff jvm_options/8_print_flags_final.txt docs/jvm_options/11_print_flags_final.txt
#opendiff jvm_options/11_print_flags_final.txt docs/jvm_options/13_print_flags_final.txt

versions=(
    "11"
    "12"
    "13"
    "14"
)

for i in $versions[@]; do
    /usr/libexec/java_home -v ${i} --exec java --list-modules > modules/jdk${i}_modules.md
done