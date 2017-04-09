namespace java com.thomas.thrift.master

struct JobInfo {
  1: string jobType,
  2: double learningRate,
  3: list<string> dataPaths,
  4: i32 iteNum,
  5: string extraParams,
  6: string parallelType
}

struct ExecInfo {
  1: i64 execTime,
  2: i64 computeTime,
  3: list<double> loss,
  4: string hostId
}

struct JobResult {
  1: list<double> params,
  2: list<ExecInfo> execInfos
}


service PMasterService {
  i64 createJob(
    1:JobInfo jobInfo
  ),

  JobResult getJobResult(
    1:i64 jobId
  ),

  bool getJobDone(
    1:i64 jobId,
    2:ExecInfo execInfo
  ),

  bool isAlive()
}
