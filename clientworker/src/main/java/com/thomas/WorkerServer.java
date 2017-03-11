package com.thomas;

import com.thomas.thrift.worker.PSWorkerService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by hadoop on 3/11/17.
 */
public class WorkerServer {
    private static int port = 8080;
    private static PSWorkerImp psWorker;
    private static Logger logger = Logger.getLogger("WorkerServer");
    private static HashMap<String, String> props;

    public static void init(String []args) {
        // init the config.
        props = new HashMap<String, String>();
        for (String str: args) {
            String [] d = str.split(":");
            props.put(d[0], d[1]);
        }
        if (props.containsKey("-p")) port = Integer.parseInt(props.get("-p"));
        // and init the ps worker.
        psWorker = new PSWorkerImp();
    }

    public static void main(String []args) {
        init(args);
        run();
    }

    public static void run() {
        try {
            logger.info("WorkerServer start at Port: " + port);

            PSWorkerService.Processor<PSWorkerService.Iface> tprocessor =
                    new PSWorkerService.Processor<PSWorkerService.Iface>(psWorker);

            TServerSocket serverTransport = new TServerSocket(port);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TCompactProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error");
            e.printStackTrace();
        }
    }
}
