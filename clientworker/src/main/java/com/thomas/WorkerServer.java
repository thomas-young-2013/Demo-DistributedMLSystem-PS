package com.thomas;

import com.thomas.thrift.worker.PSWorkerService;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
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

    private static PSWorkerService.Processor processor;

    public static void init(String []args) {

        PropertyConfigurator.configure("config/worker-log4j.properties");

        // init the config.
        props = new HashMap<String, String>();
        for (String str: args) {
            String [] d = str.split(":");
            props.put(d[0], d[1]);
        }
        if (props.containsKey("-p")) port = Integer.parseInt(props.get("-p"));
        // and init the ps worker.
        psWorker = new PSWorkerImp();

        processor = new PSWorkerService.Processor<PSWorkerService.Iface>(psWorker);
    }

    public static void main(String []args) {
        init(args);
        run();
    }

    public static void run() {
        try {
            logger.info("WorkerServer start at Port: " + port);

            TServerSocket serverTransport = new TServerSocket(port);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error");
            e.printStackTrace();
        }
    }
}
