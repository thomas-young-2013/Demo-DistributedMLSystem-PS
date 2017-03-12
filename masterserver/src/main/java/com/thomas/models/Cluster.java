package com.thomas.models;

import java.util.ArrayList;

/**
 * Created by hadoop on 3/12/17.
 */
public class Cluster {

    public ArrayList<Node> nodes;

    public ArrayList<Node> workers;

    public ArrayList<Node> servers;

    public void addNode(Node nodeInfo) {
        nodes.add(nodeInfo);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

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
}
