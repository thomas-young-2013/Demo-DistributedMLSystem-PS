#!/bin/bash

# echo "CONFIRM: execute this script in ROOT directory of this project!!!"

# app_home=`pwd`
app_home="/home/hadoop/Desktop/codes/DistributedMLSystem"
expe_home="/home/hadoop/Desktop/experiment"
py_script_home="${app_home}/common/src/main/resource/python"

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
    config_file="$expe_home/worker${t}/conf/worker.properties"
    python "${py_script_home}/config-reviser.py" $config_file $t

    # chmod
    chmod 777 "$expe_home/worker${t}/bin/start.sh"
    chmod 777 "$expe_home/worker${t}/bin/stop.sh"
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
        create_worker $t
        let "t++"
    done
}

run "$@"
