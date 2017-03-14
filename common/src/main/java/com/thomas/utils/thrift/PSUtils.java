package com.thomas.utils.thrift;

import com.thomas.models.Node;
import com.thomas.models.PSTable;
import com.thomas.thrift.server.ParameterServerService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;

import static com.thomas.utils.constant.DefaultConstant.TIMEOUT;

/**
 * Created by hadoop on 3/12/17.
 */
public class PSUtils {

    public static void createTable(PSTable psTable, ArrayList<String> machines, ArrayList<Double> initials) {
        Node node = psTable.node;
        TTransport transport = null;

        try {
            transport = new TSocket(node.hostId, node.port, TIMEOUT);
            // TProtocol protocol = new TCompactProtocol(transport);
            TProtocol protocol = new TBinaryProtocol(transport);
            ParameterServerService.Client client = new ParameterServerService.Client(protocol);
            transport.open();

            client.createTable(psTable.tableId, machines, initials);

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

    public static ArrayList<Double> getParams(Node node, int iteNum, String tableId) {
        TTransport transport = null;
        ArrayList<Double> params = new ArrayList<Double>();
        try {
            transport = new TSocket(node.hostId, node.port, TIMEOUT);
            // TProtocol protocol = new TCompactProtocol(transport);
            TProtocol protocol = new TBinaryProtocol(transport);
            ParameterServerService.Client client = new ParameterServerService.Client(protocol);
            transport.open();

            params = (ArrayList<Double>) client.readRows(tableId, iteNum, 2);

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
