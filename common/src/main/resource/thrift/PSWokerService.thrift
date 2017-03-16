namespace java com.thomas.thrift.worker

struct JobConfig {
  1: i64 jobKey,
  2: string jobType,
  3: double learningRate,
  4: string dataPath,
  5: i32 iteNum,
  6: string serverId,
  7: i32 serverPort,
  8: string tableId,
  9: string parallelType,
  10: i32 stale
}

struct Carrier {
   1: i32 iterationNum,
   2: list<list<double>> gradients
}

service PSWorkerService {

  void runJob(
    1:JobConfig jobConfig
  ),

  bool isAlive(),

  oneway void clock(
    1:string tableId,
    2:Carrier carrier)
}
