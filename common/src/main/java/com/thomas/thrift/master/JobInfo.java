/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thomas.thrift.master;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-04-09")
public class JobInfo implements org.apache.thrift.TBase<JobInfo, JobInfo._Fields>, java.io.Serializable, Cloneable, Comparable<JobInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("JobInfo");

  private static final org.apache.thrift.protocol.TField JOB_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("jobType", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField LEARNING_RATE_FIELD_DESC = new org.apache.thrift.protocol.TField("learningRate", org.apache.thrift.protocol.TType.DOUBLE, (short)2);
  private static final org.apache.thrift.protocol.TField DATA_PATHS_FIELD_DESC = new org.apache.thrift.protocol.TField("dataPaths", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField ITE_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("iteNum", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField EXTRA_PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("extraParams", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField PARALLEL_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("parallelType", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new JobInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new JobInfoTupleSchemeFactory();

  public String jobType; // required
  public double learningRate; // required
  public java.util.List<String> dataPaths; // required
  public int iteNum; // required
  public String extraParams; // required
  public String parallelType; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    JOB_TYPE((short)1, "jobType"),
    LEARNING_RATE((short)2, "learningRate"),
    DATA_PATHS((short)3, "dataPaths"),
    ITE_NUM((short)4, "iteNum"),
    EXTRA_PARAMS((short)5, "extraParams"),
    PARALLEL_TYPE((short)6, "parallelType");

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
        case 1: // JOB_TYPE
          return JOB_TYPE;
        case 2: // LEARNING_RATE
          return LEARNING_RATE;
        case 3: // DATA_PATHS
          return DATA_PATHS;
        case 4: // ITE_NUM
          return ITE_NUM;
        case 5: // EXTRA_PARAMS
          return EXTRA_PARAMS;
        case 6: // PARALLEL_TYPE
          return PARALLEL_TYPE;
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
  private static final int __LEARNINGRATE_ISSET_ID = 0;
  private static final int __ITENUM_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.JOB_TYPE, new org.apache.thrift.meta_data.FieldMetaData("jobType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LEARNING_RATE, new org.apache.thrift.meta_data.FieldMetaData("learningRate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.DATA_PATHS, new org.apache.thrift.meta_data.FieldMetaData("dataPaths", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.ITE_NUM, new org.apache.thrift.meta_data.FieldMetaData("iteNum", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.EXTRA_PARAMS, new org.apache.thrift.meta_data.FieldMetaData("extraParams", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARALLEL_TYPE, new org.apache.thrift.meta_data.FieldMetaData("parallelType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(JobInfo.class, metaDataMap);
  }

  public JobInfo() {
  }

  public JobInfo(
    String jobType,
    double learningRate,
    java.util.List<String> dataPaths,
    int iteNum,
    String extraParams,
    String parallelType)
  {
    this();
    this.jobType = jobType;
    this.learningRate = learningRate;
    setLearningRateIsSet(true);
    this.dataPaths = dataPaths;
    this.iteNum = iteNum;
    setIteNumIsSet(true);
    this.extraParams = extraParams;
    this.parallelType = parallelType;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public JobInfo(JobInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetJobType()) {
      this.jobType = other.jobType;
    }
    this.learningRate = other.learningRate;
    if (other.isSetDataPaths()) {
      java.util.List<String> __this__dataPaths = new java.util.ArrayList<String>(other.dataPaths);
      this.dataPaths = __this__dataPaths;
    }
    this.iteNum = other.iteNum;
    if (other.isSetExtraParams()) {
      this.extraParams = other.extraParams;
    }
    if (other.isSetParallelType()) {
      this.parallelType = other.parallelType;
    }
  }

  public JobInfo deepCopy() {
    return new JobInfo(this);
  }

  @Override
  public void clear() {
    this.jobType = null;
    setLearningRateIsSet(false);
    this.learningRate = 0.0;
    this.dataPaths = null;
    setIteNumIsSet(false);
    this.iteNum = 0;
    this.extraParams = null;
    this.parallelType = null;
  }

  public String getJobType() {
    return this.jobType;
  }

  public JobInfo setJobType(String jobType) {
    this.jobType = jobType;
    return this;
  }

  public void unsetJobType() {
    this.jobType = null;
  }

  /** Returns true if field jobType is set (has been assigned a value) and false otherwise */
  public boolean isSetJobType() {
    return this.jobType != null;
  }

  public void setJobTypeIsSet(boolean value) {
    if (!value) {
      this.jobType = null;
    }
  }

  public double getLearningRate() {
    return this.learningRate;
  }

  public JobInfo setLearningRate(double learningRate) {
    this.learningRate = learningRate;
    setLearningRateIsSet(true);
    return this;
  }

  public void unsetLearningRate() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LEARNINGRATE_ISSET_ID);
  }

  /** Returns true if field learningRate is set (has been assigned a value) and false otherwise */
  public boolean isSetLearningRate() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LEARNINGRATE_ISSET_ID);
  }

  public void setLearningRateIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LEARNINGRATE_ISSET_ID, value);
  }

  public int getDataPathsSize() {
    return (this.dataPaths == null) ? 0 : this.dataPaths.size();
  }

  public java.util.Iterator<String> getDataPathsIterator() {
    return (this.dataPaths == null) ? null : this.dataPaths.iterator();
  }

  public void addToDataPaths(String elem) {
    if (this.dataPaths == null) {
      this.dataPaths = new java.util.ArrayList<String>();
    }
    this.dataPaths.add(elem);
  }

  public java.util.List<String> getDataPaths() {
    return this.dataPaths;
  }

  public JobInfo setDataPaths(java.util.List<String> dataPaths) {
    this.dataPaths = dataPaths;
    return this;
  }

  public void unsetDataPaths() {
    this.dataPaths = null;
  }

  /** Returns true if field dataPaths is set (has been assigned a value) and false otherwise */
  public boolean isSetDataPaths() {
    return this.dataPaths != null;
  }

  public void setDataPathsIsSet(boolean value) {
    if (!value) {
      this.dataPaths = null;
    }
  }

  public int getIteNum() {
    return this.iteNum;
  }

  public JobInfo setIteNum(int iteNum) {
    this.iteNum = iteNum;
    setIteNumIsSet(true);
    return this;
  }

  public void unsetIteNum() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ITENUM_ISSET_ID);
  }

  /** Returns true if field iteNum is set (has been assigned a value) and false otherwise */
  public boolean isSetIteNum() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ITENUM_ISSET_ID);
  }

  public void setIteNumIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ITENUM_ISSET_ID, value);
  }

  public String getExtraParams() {
    return this.extraParams;
  }

  public JobInfo setExtraParams(String extraParams) {
    this.extraParams = extraParams;
    return this;
  }

  public void unsetExtraParams() {
    this.extraParams = null;
  }

  /** Returns true if field extraParams is set (has been assigned a value) and false otherwise */
  public boolean isSetExtraParams() {
    return this.extraParams != null;
  }

  public void setExtraParamsIsSet(boolean value) {
    if (!value) {
      this.extraParams = null;
    }
  }

  public String getParallelType() {
    return this.parallelType;
  }

  public JobInfo setParallelType(String parallelType) {
    this.parallelType = parallelType;
    return this;
  }

  public void unsetParallelType() {
    this.parallelType = null;
  }

  /** Returns true if field parallelType is set (has been assigned a value) and false otherwise */
  public boolean isSetParallelType() {
    return this.parallelType != null;
  }

  public void setParallelTypeIsSet(boolean value) {
    if (!value) {
      this.parallelType = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case JOB_TYPE:
      if (value == null) {
        unsetJobType();
      } else {
        setJobType((String)value);
      }
      break;

    case LEARNING_RATE:
      if (value == null) {
        unsetLearningRate();
      } else {
        setLearningRate((Double)value);
      }
      break;

    case DATA_PATHS:
      if (value == null) {
        unsetDataPaths();
      } else {
        setDataPaths((java.util.List<String>)value);
      }
      break;

    case ITE_NUM:
      if (value == null) {
        unsetIteNum();
      } else {
        setIteNum((Integer)value);
      }
      break;

    case EXTRA_PARAMS:
      if (value == null) {
        unsetExtraParams();
      } else {
        setExtraParams((String)value);
      }
      break;

    case PARALLEL_TYPE:
      if (value == null) {
        unsetParallelType();
      } else {
        setParallelType((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case JOB_TYPE:
      return getJobType();

    case LEARNING_RATE:
      return getLearningRate();

    case DATA_PATHS:
      return getDataPaths();

    case ITE_NUM:
      return getIteNum();

    case EXTRA_PARAMS:
      return getExtraParams();

    case PARALLEL_TYPE:
      return getParallelType();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case JOB_TYPE:
      return isSetJobType();
    case LEARNING_RATE:
      return isSetLearningRate();
    case DATA_PATHS:
      return isSetDataPaths();
    case ITE_NUM:
      return isSetIteNum();
    case EXTRA_PARAMS:
      return isSetExtraParams();
    case PARALLEL_TYPE:
      return isSetParallelType();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof JobInfo)
      return this.equals((JobInfo)that);
    return false;
  }

  public boolean equals(JobInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_jobType = true && this.isSetJobType();
    boolean that_present_jobType = true && that.isSetJobType();
    if (this_present_jobType || that_present_jobType) {
      if (!(this_present_jobType && that_present_jobType))
        return false;
      if (!this.jobType.equals(that.jobType))
        return false;
    }

    boolean this_present_learningRate = true;
    boolean that_present_learningRate = true;
    if (this_present_learningRate || that_present_learningRate) {
      if (!(this_present_learningRate && that_present_learningRate))
        return false;
      if (this.learningRate != that.learningRate)
        return false;
    }

    boolean this_present_dataPaths = true && this.isSetDataPaths();
    boolean that_present_dataPaths = true && that.isSetDataPaths();
    if (this_present_dataPaths || that_present_dataPaths) {
      if (!(this_present_dataPaths && that_present_dataPaths))
        return false;
      if (!this.dataPaths.equals(that.dataPaths))
        return false;
    }

    boolean this_present_iteNum = true;
    boolean that_present_iteNum = true;
    if (this_present_iteNum || that_present_iteNum) {
      if (!(this_present_iteNum && that_present_iteNum))
        return false;
      if (this.iteNum != that.iteNum)
        return false;
    }

    boolean this_present_extraParams = true && this.isSetExtraParams();
    boolean that_present_extraParams = true && that.isSetExtraParams();
    if (this_present_extraParams || that_present_extraParams) {
      if (!(this_present_extraParams && that_present_extraParams))
        return false;
      if (!this.extraParams.equals(that.extraParams))
        return false;
    }

    boolean this_present_parallelType = true && this.isSetParallelType();
    boolean that_present_parallelType = true && that.isSetParallelType();
    if (this_present_parallelType || that_present_parallelType) {
      if (!(this_present_parallelType && that_present_parallelType))
        return false;
      if (!this.parallelType.equals(that.parallelType))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetJobType()) ? 131071 : 524287);
    if (isSetJobType())
      hashCode = hashCode * 8191 + jobType.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(learningRate);

    hashCode = hashCode * 8191 + ((isSetDataPaths()) ? 131071 : 524287);
    if (isSetDataPaths())
      hashCode = hashCode * 8191 + dataPaths.hashCode();

    hashCode = hashCode * 8191 + iteNum;

    hashCode = hashCode * 8191 + ((isSetExtraParams()) ? 131071 : 524287);
    if (isSetExtraParams())
      hashCode = hashCode * 8191 + extraParams.hashCode();

    hashCode = hashCode * 8191 + ((isSetParallelType()) ? 131071 : 524287);
    if (isSetParallelType())
      hashCode = hashCode * 8191 + parallelType.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(JobInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetJobType()).compareTo(other.isSetJobType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobType, other.jobType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLearningRate()).compareTo(other.isSetLearningRate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLearningRate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.learningRate, other.learningRate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDataPaths()).compareTo(other.isSetDataPaths());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataPaths()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataPaths, other.dataPaths);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIteNum()).compareTo(other.isSetIteNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIteNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.iteNum, other.iteNum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExtraParams()).compareTo(other.isSetExtraParams());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExtraParams()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.extraParams, other.extraParams);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParallelType()).compareTo(other.isSetParallelType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParallelType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parallelType, other.parallelType);
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
    StringBuilder sb = new StringBuilder("JobInfo(");
    boolean first = true;

    sb.append("jobType:");
    if (this.jobType == null) {
      sb.append("null");
    } else {
      sb.append(this.jobType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("learningRate:");
    sb.append(this.learningRate);
    first = false;
    if (!first) sb.append(", ");
    sb.append("dataPaths:");
    if (this.dataPaths == null) {
      sb.append("null");
    } else {
      sb.append(this.dataPaths);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("iteNum:");
    sb.append(this.iteNum);
    first = false;
    if (!first) sb.append(", ");
    sb.append("extraParams:");
    if (this.extraParams == null) {
      sb.append("null");
    } else {
      sb.append(this.extraParams);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("parallelType:");
    if (this.parallelType == null) {
      sb.append("null");
    } else {
      sb.append(this.parallelType);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class JobInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public JobInfoStandardScheme getScheme() {
      return new JobInfoStandardScheme();
    }
  }

  private static class JobInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<JobInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, JobInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // JOB_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.jobType = iprot.readString();
              struct.setJobTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LEARNING_RATE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.learningRate = iprot.readDouble();
              struct.setLearningRateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATA_PATHS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.dataPaths = new java.util.ArrayList<String>(_list0.size);
                String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.dataPaths.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setDataPathsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ITE_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.iteNum = iprot.readI32();
              struct.setIteNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // EXTRA_PARAMS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.extraParams = iprot.readString();
              struct.setExtraParamsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // PARALLEL_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.parallelType = iprot.readString();
              struct.setParallelTypeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, JobInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.jobType != null) {
        oprot.writeFieldBegin(JOB_TYPE_FIELD_DESC);
        oprot.writeString(struct.jobType);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(LEARNING_RATE_FIELD_DESC);
      oprot.writeDouble(struct.learningRate);
      oprot.writeFieldEnd();
      if (struct.dataPaths != null) {
        oprot.writeFieldBegin(DATA_PATHS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.dataPaths.size()));
          for (String _iter3 : struct.dataPaths)
          {
            oprot.writeString(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ITE_NUM_FIELD_DESC);
      oprot.writeI32(struct.iteNum);
      oprot.writeFieldEnd();
      if (struct.extraParams != null) {
        oprot.writeFieldBegin(EXTRA_PARAMS_FIELD_DESC);
        oprot.writeString(struct.extraParams);
        oprot.writeFieldEnd();
      }
      if (struct.parallelType != null) {
        oprot.writeFieldBegin(PARALLEL_TYPE_FIELD_DESC);
        oprot.writeString(struct.parallelType);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class JobInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public JobInfoTupleScheme getScheme() {
      return new JobInfoTupleScheme();
    }
  }

  private static class JobInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<JobInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, JobInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetJobType()) {
        optionals.set(0);
      }
      if (struct.isSetLearningRate()) {
        optionals.set(1);
      }
      if (struct.isSetDataPaths()) {
        optionals.set(2);
      }
      if (struct.isSetIteNum()) {
        optionals.set(3);
      }
      if (struct.isSetExtraParams()) {
        optionals.set(4);
      }
      if (struct.isSetParallelType()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetJobType()) {
        oprot.writeString(struct.jobType);
      }
      if (struct.isSetLearningRate()) {
        oprot.writeDouble(struct.learningRate);
      }
      if (struct.isSetDataPaths()) {
        {
          oprot.writeI32(struct.dataPaths.size());
          for (String _iter4 : struct.dataPaths)
          {
            oprot.writeString(_iter4);
          }
        }
      }
      if (struct.isSetIteNum()) {
        oprot.writeI32(struct.iteNum);
      }
      if (struct.isSetExtraParams()) {
        oprot.writeString(struct.extraParams);
      }
      if (struct.isSetParallelType()) {
        oprot.writeString(struct.parallelType);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, JobInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.jobType = iprot.readString();
        struct.setJobTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.learningRate = iprot.readDouble();
        struct.setLearningRateIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.dataPaths = new java.util.ArrayList<String>(_list5.size);
          String _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = iprot.readString();
            struct.dataPaths.add(_elem6);
          }
        }
        struct.setDataPathsIsSet(true);
      }
      if (incoming.get(3)) {
        struct.iteNum = iprot.readI32();
        struct.setIteNumIsSet(true);
      }
      if (incoming.get(4)) {
        struct.extraParams = iprot.readString();
        struct.setExtraParamsIsSet(true);
      }
      if (incoming.get(5)) {
        struct.parallelType = iprot.readString();
        struct.setParallelTypeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

