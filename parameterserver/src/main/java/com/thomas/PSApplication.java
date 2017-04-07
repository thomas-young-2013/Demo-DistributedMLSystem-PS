package com.thomas;

import com.thomas.thrift.server.ParameterServerService;
import com.thomas.utils.ResourceLoader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import java.util.Properties;

/**
 * Created by hadoop on 3/8/17.
 */
public class PSApplication {
    public int port = 8000;
    private Logger logger = Logger.getLogger(this.getClass());
    public Properties props;
    /**
     * @param args
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure("conf/server-log4j.properties");

        PSApplication application = new PSApplication();
        application.init(args);
        application.run();
    }

    public void init(String []args) {
        props = new Properties();
        // load properties from file.
        ResourceLoader.propsFileParser(props, "conf/server.properties");
        // load part properties from command, if conflicts, override the one in file.
        ResourceLoader.cmdParser(props, args);
        if (props.getProperty("port") != null) port = Integer.parseInt(props.getProperty("port"));
    }

    public void run() {
        try {
            logger.info("Parameter Server start at port: " + port);

            ParameterServerService.Processor<ParameterServerService.Iface> tprocessor =
                    new ParameterServerService.Processor<ParameterServerService.Iface>(new ParameterServerServiceImp());

            TServerSocket serverTransport = new TServerSocket(port);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(tprocessor));

            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }

}
