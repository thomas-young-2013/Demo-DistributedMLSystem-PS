package com.thomas;

import com.thomas.models.Master;
import com.thomas.thrift.master.PMasterService;
import com.thomas.utils.ResourceLoader;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by thomas young on 3/12/17.
 */
public class PMasterServer {

    private static PMasterServiceImp pMaster;
    private static Logger logger = Logger.getLogger("MasterServer");
    public static Properties props;
    // 9000 default for master node.
    private static int port = 9000;

    public static void init(String []args) {
        // init the log system.
        PropertyConfigurator.configure("conf/master-log4j.properties");

        props = new Properties();
        // load properties from file.
        ResourceLoader.propsFileParser(props, "conf/master.properties");
        // load part properties from command, if conflicts, override the one in file.
        ResourceLoader.cmdParser(props, args);
        if (props.getProperty("port") != null) port = Integer.parseInt(props.getProperty("port"));

        // init the master from property file: server and worker info.
        Master master = new Master();
        master.init(props);
        pMaster = new PMasterServiceImp(master);
        // System.out.println(props);
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
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(tprocessor));

            server.serve();

        } catch (Exception e) {
            System.out.println("Master Server starts failed!");
            e.printStackTrace();
        }
    }
}
