#!/bin/bash

# echo "CONFIRM: execute this script in ROOT directory of this project!!!"

# app_home=`pwd`
app_home="/home/hadoop/Desktop/codes/DistributedMLSystem"
expe_home="/home/hadoop/Desktop/experiment"

declare -i n_worker=1

process_args () {
    n_worker="$1"
}

create_worker() {
    # first copy the binary.
    t="$1"
    cp -rf "${app_home}/project/out/distributedMLSystem/worker/" "$expe_home/"
    mv "$expe_home/worker" "$expe_home/worker${t}"
    # change the conf file.

}

run() {
    process_args "$@"

    # clean the env
    if [[ -d $expe_home ]]; then
        cd $expe_home
        rm -rf *
    else
        exit 1
    fi

    # prepare the workers
    t=1
    while(( $t<=$n_worker ))
    do
        echo $t
        create_worker $t
        let "t++"
    done
}

run "$@"
