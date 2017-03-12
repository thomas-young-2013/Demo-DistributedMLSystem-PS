package com.thomas;

import com.thomas.models.Master;
import com.thomas.thrift.master.PMasterService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by hadoop on 3/12/17.
 */
public class PMasterServer {

    private static int port = 8080;
    private static PMasterServiceImp pMaster;
    private static Logger logger = Logger.getLogger("MasterServer");
    private static HashMap<String, String> props;

    public static void init(String []args) {
        // init the config.
        props = new HashMap<String, String>();
        for (String str: args) {
            String [] d = str.split(":");
            props.put(d[0], d[1]);
        }
        if (props.containsKey("-p")) port = Integer.parseInt(props.get("-p"));

        // init the master from property file: server and worker info.
        Master master = new Master();
        master.init();
        pMaster = new PMasterServiceImp(master);
    }

    public static void main(String []args) {
        init(args);
        run();
    }

    public static void run() {
        try {
            logger.info("Master Server start at: " + port);

            PMasterService.Processor<PMasterService.Iface> tprocessor =
                    new PMasterService.Processor<PMasterService.Iface>(pMaster);

            TServerSocket serverTransport = new TServerSocket(port);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TCompactProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Master Server starts failed!");
            e.printStackTrace();
        }
    }
}
