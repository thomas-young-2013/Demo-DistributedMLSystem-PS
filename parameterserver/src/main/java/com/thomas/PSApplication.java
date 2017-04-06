package com.thomas;

import com.thomas.thrift.server.ParameterServerService;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

/**
 * Created by hadoop on 3/8/17.
 */
public class PSApplication {
    public static final int SERVER_PORT = 8000;
    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * @param args
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure("conf/log4j.properties");

        PSApplication application = new PSApplication();
        application.run();
    }

    public void run() {
        try {
            logger.info("HelloWorld TSimpleServer start ....");

            ParameterServerService.Processor<ParameterServerService.Iface> tprocessor =
                    new ParameterServerService.Processor<ParameterServerService.Iface>(new ParameterServerServiceImp());

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            /*TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);

            tArgs.protocolFactory(new TCompactProtocol.Factory());*/

            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(tprocessor));

            // TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }

}
