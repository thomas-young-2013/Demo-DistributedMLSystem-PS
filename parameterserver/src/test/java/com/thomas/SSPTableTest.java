package com.thomas;

import com.thomas.models.SSPParameterTable;
import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.server.ParameterServerService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 3/16/17.
 */
public class SSPTableTest {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8000;
    public static final int TIMEOUT = 30000;

    public static void main(String[] args) {
        SSPTableTest worker = new SSPTableTest();
        worker.run();
    }

    public void run() {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0.1);
        list.add(0.1);
        list.add(0.1);
        List<String> machines = new ArrayList<String>();
        machines.add("worker1");
        machines.add("worker2");
        List<List<Double>> list1 = new ArrayList<List<Double>>();
        list1.add(list);
        Carrier carrier = new Carrier(0, list1);

        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            transport.open();
            // TProtocol protocol = new TCompactProtocol(transport);
            TProtocol protocol = new TBinaryProtocol(transport);
            ParameterServerService.Client client = new ParameterServerService.Client(protocol);


            // client.create("SSP:3", "lr", machines, carrier);
            /*Carrier carrier1 = client.read(SERVER_IP, "lr", 1, 3);
            System.out.println(carrier1);*/

            // list1.add(list);
            /*Carrier carrier2 = new Carrier(5, list1);
            client.update(SERVER_IP, "lr", carrier2);
            carrier2.iterationNum = 6;
            client.update(SERVER_IP, "lr", carrier2);*/

            Carrier carrier3 = client.read(SERVER_IP, "lr", 3, 3);
            System.out.println(carrier3);

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
