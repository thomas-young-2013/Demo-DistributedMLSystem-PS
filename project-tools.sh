#!/bin/bash

echo "CONFIRM: execute this script in ROOT directory of this project!!!"

build() {
    script_one="./common/src/main/resource/shell/project-builder.sh"
    if [ ! -x $script_one ]; then
        chmod 777 $script_one
    fi
    $script_one
}

deploy() {
    script="./common/src/main/resource/shell/executables-producer.sh"
    if [ ! -x $script ]; then
        chmod 777 $script
    fi
    $script
}

###  ------------------------------- ###
###  Start of customized settings    ###
###  ------------------------------- ###

usage() {
 cat <<EOM
Usage: $script_name [options]

  -b | -build        maven build this project.
  -d | -deploy        produce the binary.
  -h | -help         help messages.
EOM
}

# Processes incoming arguments and places them in appropriate global variables.  called by the run method.
process_args () {
    case "$1" in
       -h|-help) usage; exit 1 ;;
    -d|-deploy) deploy ;;
      -b|-build) build ;;
    esac
}

run() {
    process_args "$@"
}

# main entry.
run "$@"
