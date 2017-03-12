package com.thomas.models;

import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.server.ParameterServerService;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.utils.constant.NodeStatus;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

import static com.thomas.utils.constant.DefaultConstant.TIMEOUT;

/**
 * Created by hadoop on 3/12/17.
 */
public class Job {
    public long jobId;
    public String jobType;
    public int iteNum;
    public double learningRate;
    public List<String> dataPaths;
    public int status;

    public List<Node> parameterServers;
    public List<Node> workers;
    public List<JobConfig> jobConfigs;

    public List<PSTable> tables;
    public List<ExecInfo> execInfo;

    public Job(long jobId, JobInfo jobInfo) {
        this.jobId = jobId;
        this.jobType = jobInfo.jobType;
        this.iteNum = jobInfo.iteNum;
        this.learningRate = jobInfo.learningRate;
        this.dataPaths = jobInfo.dataPaths;
        this.status = NodeStatus.RUNNING;

        this.parameterServers = new ArrayList<Node>();
        this.workers = new ArrayList<Node>();
        this.execInfo = new ArrayList<ExecInfo>();
        this.tables = new ArrayList<PSTable>();
        this.jobConfigs = new ArrayList<JobConfig>();
    }

    public ArrayList<Double> getParamsResult() {
        TTransport transport = null;
        ArrayList<Double> params = new ArrayList<Double>();
        try {
            Node node = parameterServers.get(0);
            transport = new TSocket(node.hostId, node.port, TIMEOUT);
            TProtocol protocol = new TCompactProtocol(transport);
            ParameterServerService.Client client = new ParameterServerService.Client(protocol);
            transport.open();

            params = (ArrayList<Double>) client.readRows(tables.get(0).tableId, iteNum, 2);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
            return params;
        }
    }
}
