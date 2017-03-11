package com.thomas;

import com.thomas.thrift.server.ParameterServerService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;

/**
 * Created by hadoop on 3/8/17.
 */
public class PSServerTest {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8000;
    public static final int TIMEOUT = 30000;

    public static void main(String[] args) {
        PSServerTest worker = new PSServerTest();
        worker.run();
    }

    public void run() {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0.1);
        list.add(0.1);
        list.add(0.1);
        ArrayList<String> machines = new ArrayList<String>();
        machines.add("worker1");

        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            TProtocol protocol = new TCompactProtocol(transport);
            ParameterServerService.Client client = new ParameterServerService.Client(protocol);
            transport.open();

            /** client.createTable("lr", machines, list);
            System.out.println(client.readRows("lr", 1000, 2));
            System.out.println(client.round("lr"));

            /*client.updateRows("lr", 0, list);
            System.out.println(client.round("lr"));

            System.out.println(client.readRows("lr", 0, 2));*/

            /*client.createTable("lr", machines, list);

            for (int i = 0; i < 100; i++) {
                while(i != 0 && client.round("lr") == false) {}
                ArrayList<Double> params = (ArrayList<Double>) client.readRows("lr", i, 0);
                client.updateRows("lr", i, list);
            }*/

            for (int i=0; i<1000; i++) {
                if (i%1==0) System.out.println(client.readRows("lr", i, 2));
            }

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
