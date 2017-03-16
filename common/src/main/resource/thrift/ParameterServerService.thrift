namespace java com.thomas.thrift.server
struct Carrier {
   1: i32 iterationNum,
   2: list<list<double>> gradients
}

service ParameterServerService {
  bool update(
    1: string hostId,
    2: string tableId,
    3: Carrier carrier
  ),

  Carrier read(
    1: string hostId,
    2: string tableId,
    3: i32 t,
    4: i32 extra
  ),

  bool create(
    1: string tableType,
    2: string tableId,
    3: list<string> machines,
    4: Carrier initials
  )
}
