/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thomas.thrift.master;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-04-09")
public class JobResult implements org.apache.thrift.TBase<JobResult, JobResult._Fields>, java.io.Serializable, Cloneable, Comparable<JobResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("JobResult");

  private static final org.apache.thrift.protocol.TField PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("params", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField EXEC_INFOS_FIELD_DESC = new org.apache.thrift.protocol.TField("execInfos", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new JobResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new JobResultTupleSchemeFactory();

  public java.util.List<Double> params; // required
  public java.util.List<ExecInfo> execInfos; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PARAMS((short)1, "params"),
    EXEC_INFOS((short)2, "execInfos");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PARAMS
          return PARAMS;
        case 2: // EXEC_INFOS
          return EXEC_INFOS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PARAMS, new org.apache.thrift.meta_data.FieldMetaData("params", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE))));
    tmpMap.put(_Fields.EXEC_INFOS, new org.apache.thrift.meta_data.FieldMetaData("execInfos", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ExecInfo.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(JobResult.class, metaDataMap);
  }

  public JobResult() {
  }

  public JobResult(
    java.util.List<Double> params,
    java.util.List<ExecInfo> execInfos)
  {
    this();
    this.params = params;
    this.execInfos = execInfos;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public JobResult(JobResult other) {
    if (other.isSetParams()) {
      java.util.List<Double> __this__params = new java.util.ArrayList<Double>(other.params);
      this.params = __this__params;
    }
    if (other.isSetExecInfos()) {
      java.util.List<ExecInfo> __this__execInfos = new java.util.ArrayList<ExecInfo>(other.execInfos.size());
      for (ExecInfo other_element : other.execInfos) {
        __this__execInfos.add(new ExecInfo(other_element));
      }
      this.execInfos = __this__execInfos;
    }
  }

  public JobResult deepCopy() {
    return new JobResult(this);
  }

  @Override
  public void clear() {
    this.params = null;
    this.execInfos = null;
  }

  public int getParamsSize() {
    return (this.params == null) ? 0 : this.params.size();
  }

  public java.util.Iterator<Double> getParamsIterator() {
    return (this.params == null) ? null : this.params.iterator();
  }

  public void addToParams(double elem) {
    if (this.params == null) {
      this.params = new java.util.ArrayList<Double>();
    }
    this.params.add(elem);
  }

  public java.util.List<Double> getParams() {
    return this.params;
  }

  public JobResult setParams(java.util.List<Double> params) {
    this.params = params;
    return this;
  }

  public void unsetParams() {
    this.params = null;
  }

  /** Returns true if field params is set (has been assigned a value) and false otherwise */
  public boolean isSetParams() {
    return this.params != null;
  }

  public void setParamsIsSet(boolean value) {
    if (!value) {
      this.params = null;
    }
  }

  public int getExecInfosSize() {
    return (this.execInfos == null) ? 0 : this.execInfos.size();
  }

  public java.util.Iterator<ExecInfo> getExecInfosIterator() {
    return (this.execInfos == null) ? null : this.execInfos.iterator();
  }

  public void addToExecInfos(ExecInfo elem) {
    if (this.execInfos == null) {
      this.execInfos = new java.util.ArrayList<ExecInfo>();
    }
    this.execInfos.add(elem);
  }

  public java.util.List<ExecInfo> getExecInfos() {
    return this.execInfos;
  }

  public JobResult setExecInfos(java.util.List<ExecInfo> execInfos) {
    this.execInfos = execInfos;
    return this;
  }

  public void unsetExecInfos() {
    this.execInfos = null;
  }

  /** Returns true if field execInfos is set (has been assigned a value) and false otherwise */
  public boolean isSetExecInfos() {
    return this.execInfos != null;
  }

  public void setExecInfosIsSet(boolean value) {
    if (!value) {
      this.execInfos = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PARAMS:
      if (value == null) {
        unsetParams();
      } else {
        setParams((java.util.List<Double>)value);
      }
      break;

    case EXEC_INFOS:
      if (value == null) {
        unsetExecInfos();
      } else {
        setExecInfos((java.util.List<ExecInfo>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PARAMS:
      return getParams();

    case EXEC_INFOS:
      return getExecInfos();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PARAMS:
      return isSetParams();
    case EXEC_INFOS:
      return isSetExecInfos();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof JobResult)
      return this.equals((JobResult)that);
    return false;
  }

  public boolean equals(JobResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_params = true && this.isSetParams();
    boolean that_present_params = true && that.isSetParams();
    if (this_present_params || that_present_params) {
      if (!(this_present_params && that_present_params))
        return false;
      if (!this.params.equals(that.params))
        return false;
    }

    boolean this_present_execInfos = true && this.isSetExecInfos();
    boolean that_present_execInfos = true && that.isSetExecInfos();
    if (this_present_execInfos || that_present_execInfos) {
      if (!(this_present_execInfos && that_present_execInfos))
        return false;
      if (!this.execInfos.equals(that.execInfos))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetParams()) ? 131071 : 524287);
    if (isSetParams())
      hashCode = hashCode * 8191 + params.hashCode();

    hashCode = hashCode * 8191 + ((isSetExecInfos()) ? 131071 : 524287);
    if (isSetExecInfos())
      hashCode = hashCode * 8191 + execInfos.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(JobResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetParams()).compareTo(other.isSetParams());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParams()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.params, other.params);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExecInfos()).compareTo(other.isSetExecInfos());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExecInfos()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.execInfos, other.execInfos);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("JobResult(");
    boolean first = true;

    sb.append("params:");
    if (this.params == null) {
      sb.append("null");
    } else {
      sb.append(this.params);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("execInfos:");
    if (this.execInfos == null) {
      sb.append("null");
    } else {
      sb.append(this.execInfos);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class JobResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public JobResultStandardScheme getScheme() {
      return new JobResultStandardScheme();
    }
  }

  private static class JobResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<JobResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, JobResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PARAMS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list16 = iprot.readListBegin();
                struct.params = new java.util.ArrayList<Double>(_list16.size);
                double _elem17;
                for (int _i18 = 0; _i18 < _list16.size; ++_i18)
                {
                  _elem17 = iprot.readDouble();
                  struct.params.add(_elem17);
                }
                iprot.readListEnd();
              }
              struct.setParamsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // EXEC_INFOS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list19 = iprot.readListBegin();
                struct.execInfos = new java.util.ArrayList<ExecInfo>(_list19.size);
                ExecInfo _elem20;
                for (int _i21 = 0; _i21 < _list19.size; ++_i21)
                {
                  _elem20 = new ExecInfo();
                  _elem20.read(iprot);
                  struct.execInfos.add(_elem20);
                }
                iprot.readListEnd();
              }
              struct.setExecInfosIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, JobResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.params != null) {
        oprot.writeFieldBegin(PARAMS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.DOUBLE, struct.params.size()));
          for (double _iter22 : struct.params)
          {
            oprot.writeDouble(_iter22);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.execInfos != null) {
        oprot.writeFieldBegin(EXEC_INFOS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.execInfos.size()));
          for (ExecInfo _iter23 : struct.execInfos)
          {
            _iter23.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class JobResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public JobResultTupleScheme getScheme() {
      return new JobResultTupleScheme();
    }
  }

  private static class JobResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<JobResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, JobResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetParams()) {
        optionals.set(0);
      }
      if (struct.isSetExecInfos()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetParams()) {
        {
          oprot.writeI32(struct.params.size());
          for (double _iter24 : struct.params)
          {
            oprot.writeDouble(_iter24);
          }
        }
      }
      if (struct.isSetExecInfos()) {
        {
          oprot.writeI32(struct.execInfos.size());
          for (ExecInfo _iter25 : struct.execInfos)
          {
            _iter25.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, JobResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list26 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.DOUBLE, iprot.readI32());
          struct.params = new java.util.ArrayList<Double>(_list26.size);
          double _elem27;
          for (int _i28 = 0; _i28 < _list26.size; ++_i28)
          {
            _elem27 = iprot.readDouble();
            struct.params.add(_elem27);
          }
        }
        struct.setParamsIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list29 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.execInfos = new java.util.ArrayList<ExecInfo>(_list29.size);
          ExecInfo _elem30;
          for (int _i31 = 0; _i31 < _list29.size; ++_i31)
          {
            _elem30 = new ExecInfo();
            _elem30.read(iprot);
            struct.execInfos.add(_elem30);
          }
        }
        struct.setExecInfosIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

