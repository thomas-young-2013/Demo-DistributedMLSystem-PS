package com.thomas;

import com.thomas.thrift.worker.PSWorkerService;
import com.thomas.utils.ResourceLoader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import java.util.Properties;

/**
 * Created by hadoop on 3/11/17.
 */
public class WorkerServer {
    private int port = 8080;
    private PSWorkerImp psWorker;
    private PSWorkerService.Processor processor;
    private Logger logger = Logger.getLogger(this.getClass());
    public Properties props;

    public void init(String []args) {
        // configure the log.
        PropertyConfigurator.configure("conf/worker-log4j.properties");

        props = new Properties();
        // load properties from file.
        ResourceLoader.propsFileParser(props, "conf/worker.properties");
        // load part properties from command, if conflicts, override the one in file.
        ResourceLoader.cmdParser(props, args);
        if (props.getProperty("port") != null) port = Integer.parseInt(props.getProperty("port"));

        // and init the ps worker.
        psWorker = new PSWorkerImp();

        processor = new PSWorkerService.Processor<PSWorkerService.Iface>(psWorker);
    }

    public static void main(String []args) {
        WorkerServer workerServer = new WorkerServer();
        workerServer.init(args);
        workerServer.run();
    }

    public void run() {
        try {
            logger.info("WorkerServer start at Port: " + port);

            TServerSocket serverTransport = new TServerSocket(port);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            server.serve();

        } catch (Exception e) {
            logger.error("WorkerServer start error!");
            e.printStackTrace();
        }
    }
}
