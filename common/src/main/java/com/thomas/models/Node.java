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

    public static Node getNodeFromString(String str) {
        String []data = str.split(":");
        if (data.length > 1) {
            return new Node(data[0], Integer.parseInt(data[1]));
        }
        return null;
    }
}
