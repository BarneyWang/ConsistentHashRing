package me.heng;

import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistencyHashRing {


    String[] SERVERS = new String[]{"192.168.1:1000", "192.168.1:1001", "192.168.1:1002", "192.168.1:1003", "192.168.1:1004"};

    LinkedList<String> SERVER_LIST = new LinkedList<>();

    private static final int VIRTUAL_NODE_AMOUNT = 5;

    SortedMap<Integer, String> VIRTUAL_NODES = new TreeMap<>();


    public ConsistencyHashRing() {
            // add SERVERS to SERVER_LIST
        for (int i = 0; i < SERVERS.length; i++) {
            SERVER_LIST.add(SERVERS[i]);
        }

        // init
        for (String server : SERVER_LIST) {

            for (int i = 0; i < VIRTUAL_NODE_AMOUNT; i++) {
                 String virtualServer = server+"&&"+i;
                 int key = getHash(virtualServer);
                 VIRTUAL_NODES.put(key, virtualServer);
            }
        }
        System.out.println("virtual node are all initialized");

    }

    //TODO add node
    //TODO delete node


    public String getRealServer(String requestNode){
        int hash = getHash(requestNode);
        SortedMap<Integer, String> subMap = VIRTUAL_NODES.tailMap(hash);
        Integer node = subMap.firstKey();
        String virtualNode = VIRTUAL_NODES.get(node);
        return virtualNode.split("&&")[0];
    }

    public static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        return Math.abs(hash);

    }
}
