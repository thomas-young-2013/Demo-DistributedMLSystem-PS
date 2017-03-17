package com.thomas.models;

import com.thomas.utils.constant.NodeStatus;

/**
 * Created by hadoop on 3/12/17.
 */
public class Node {
    public String hostId;
    public int port;
    public int status;

    public Node(String hostId, int port) {
        this.hostId = hostId;
        this.port = port;
        this.status = NodeStatus.RUNNING;
    }

    @Override
    public String toString() {
        return "Node{" +
                "hostId='" + hostId + '\'' +
                ", port=" + port +
                ", status=" + status +
                '}';
    }
}
