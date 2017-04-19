# DistributedMLSystem
this project implements: a simplified distributed ML framework, which support BSP and SSP parallelism.

### Readings Relative
[1] Mu Li,David G.Andersen,Jun Woo Park,Alexander J.Smola, Amr Ahmedy, Vanja Josifovski,James Long,Eugene J.Shekita, Bor-Yiing Su; Scaling Distributed Machine Learning with the Parameter Server; Carnegie Mellon University,Baidu,Google; 
[2] Qirong Ho,James Cipar,Henggang Cui,Jin Kyu Kim,Seunghak Lee,Phillip B.Gibbons, Garth A.Gibson, Gregory R.Ganger, Eric P.Xing; More Effective Distributed ML via a Stale Synchronous Parallel Parameter Server; School of Computer Science、Carnegie Mellon University,Electrical and Computer Engineering,Carnegie Mellon University,Intel Labs 

### Environment
1. operating system: linux
2. jdk: 1.8 and later

### Scripts related:
* project-tools.sh
* exp-tools.sh
* analysis-tools.sh

### Compile & Package
run script `project-tools.sh` with following command (in project ROOT directory):

`./project-tools.sh [options]`
```、
Usage:  [options]

  -b | -build        maven build this project.
  -d | -deploy        produce the binary.
  -h | -help         help messages.
  -t | -test         test in local
```

### Deploy
first you need to modify the value of `expe_home` in file `./common/src/main/resource/shell/experiment-helper.sh` according to your self demand

run script `exp-tools.sh` with following command (in project ROOT directory):

`./exp-tools.sh run 4`: it means we create 4 workers and 1 master and 1 server

if we want to stop all these services, we only type the following command in terminal:
`./exp-tools.sh stop`

### Run
we can run training job on the config in `Deploy` stage above using:

`./analysis-tools.sh run [parallel_type] [iteration_num] [learning_rate]`
for example:

`./analysis-tools.sh run SSP:4 1000 0.001`: run SSP version with stale 4, the iteration number is 1000 withe learning rate is 0.001

### End
if you have some ideas or questions, please contact us.