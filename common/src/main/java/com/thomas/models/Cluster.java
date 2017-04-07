package com.thomas.models;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by hadoop on 3/12/17.
 */
public class Cluster {

    public ArrayList<Node> workers;

    public ArrayList<Node> servers;

    public ArrayList<Node> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Node> workers) {
        this.workers = workers;
    }

    public ArrayList<Node> getServers() {
        return servers;
    }

    public void setServers(ArrayList<Node> servers) {
        this.servers = servers;
    }

    public Cluster() {
        this.workers = new ArrayList<Node>();
        this.servers = new ArrayList<Node>();
    }
    public void init(Properties props) {
        String []workerList = props.getProperty("worker").split(",");
        String []serverList = props.getProperty("server").split(",");
        // init worker nodes from props.
        for (String worker: workerList) {
            String []tmp = worker.split(":");
            if (tmp.length < 2) continue;
            workers.add(new Node(tmp[0], Integer.parseInt(tmp[1])));
        }

        // init server nodes from props.
        for (String server: serverList) {
            String []tmp = server.split(":");
            if (tmp.length < 2) continue;
            servers.add(new Node(tmp[0], Integer.parseInt(tmp[1])));
        }
    }
}
