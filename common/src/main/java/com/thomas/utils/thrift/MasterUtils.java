package com.thomas.utils.thrift;

import com.thomas.models.Node;
import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.PMasterService;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.thrift.worker.PSWorkerService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import static com.thomas.utils.constant.DefaultConstant.TIMEOUT;

/**
 * Created by hadoop on 4/8/17.
 */
public class MasterUtils {
    public static boolean getJobDone(Node node, Long jobId, ExecInfo execInfo) {
        TTransport transport = null;
        try {
            transport = new TSocket(node.hostId, node.port, TIMEOUT);
            TProtocol protocol = new TBinaryProtocol(transport);
            PMasterService.Client client = new PMasterService.Client(protocol);
            transport.open();

            client.getJobDone(jobId, execInfo);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
        return true;
    }
}
