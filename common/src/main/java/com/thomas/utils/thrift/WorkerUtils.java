package com.thomas.utils.thrift;

import com.thomas.models.Node;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.thrift.worker.PSWorkerService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import static com.thomas.utils.constant.DefaultConstant.TIMEOUT;

/**
 * Created by hadoop on 3/12/17.
 */
public class WorkerUtils {

    public static void assignJob(Node node, JobConfig jobConfig) {
        TTransport transport = null;
        try {
            transport = new TSocket(node.hostId, node.port, TIMEOUT);
            TProtocol protocol = new TBinaryProtocol(transport);
            PSWorkerService.Client client = new PSWorkerService.Client(protocol);
            transport.open();

            client.runJob(jobConfig);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

}
