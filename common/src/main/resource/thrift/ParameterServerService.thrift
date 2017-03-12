namespace java com.thomas.thrift.server

service ParameterServerService {
  list<double> readRows(
    1:string tableIdentifier,
    2:i32 rowId,
    3:i32 extra
  ),
  
  void updateRows(
    1:string tableIdentifier,
    2:i32 rowId,
    3:list<double> values
  ),

  bool round(
    1:string tableIdentifier
  ),

  bool createTable(
    1:string tableId,
    2:list<string> machines,
    3:list<double> initialVal
  )
}

