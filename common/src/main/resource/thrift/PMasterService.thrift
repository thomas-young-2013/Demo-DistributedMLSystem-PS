namespace java com.thomas.thrift.master

struct JobInfo {
  1: string jobType,
  2: double learningRate,
  3: list<string> dataPaths,
  4: i32 iteNum
}

struct ExecInfo {
  1: i64 execTime,
  2: double loss,
  3: string hostId
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
    1:string hostId,
    2:i64 jobId,
    3:ExecInfo execInfo
  ),
  
  bool isAlive()
}

