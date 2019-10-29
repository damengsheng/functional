#!/usr/bin/env zsh

versions=(
    "1.8"
    "11"
    "13"
    "14"
)

function print_flags() {

  jvm_edition="server"
  if [[ $1 == "client" ||  $1 == "server" ]]; then
      jvm_edition=$1
      echo "$jvm_edition"
  fi

  # shellcheck disable=SC1072
  if [[ "${i}" == "1.8" ]]; then
      /usr/libexec/java_home -v ${2} --exec java -${jvm_edition} -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsInitial -version > jvm_options/${i}_${jvm_edition}_print_flags_initial.txt
      /usr/libexec/java_home -v ${2} --exec java -${jvm_edition} -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version > jvm_options/${i}_${jvm_edition}_print_flags_final.txt
  else
      /usr/libexec/java_home -v ${2} --exec java -${jvm_edition} -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsInitial -XX:+PrintFlagsRanges -version > jvm_options/${i}_${jvm_edition}_print_flags_initial.txt
      /usr/libexec/java_home -v ${2} --exec java -${jvm_edition} -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -XX:+PrintFlagsRanges -version > jvm_options/${i}_${jvm_edition}_print_flags_final.txt
      /usr/libexec/java_home -v ${2} --exec java -${jvm_edition} -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsInitial -XX:+PrintFlagsFinal -XX:+PrintFlagsRanges -version > jvm_options/${i}_${jvm_edition}_print_flags_init_final.txt
  fi

}

for i in "${versions[@]}"; do
    print_flags "client" ${i}
    print_flags "server" ${i}
done



#opendiff jvm_options/11_print_flags_initial.txt docs/jvm_options/11_print_flags_final.txt
#opendiff jvm_options/8_print_flags_initial.txt docs/jvm_options/8_print_flags_final.txt
#
#opendiff jvm_options/8_print_flags_final.txt docs/jvm_options/11_print_flags_final.txt
#opendiff jvm_options/11_print_flags_final.txt docs/jvm_options/13_print_flags_final.txt

versions=(
    "11"
    "13"
    "14"
)

for i in "${versions[@]}"; do
    /usr/libexec/java_home -v ${i} --exec java --list-modules > modules/jdk${i}_modules.md
done