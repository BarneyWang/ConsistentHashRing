package me.heng;

public class Main {

    public static void main(String[] args) {
        ConsistencyHashRing chr = new ConsistencyHashRing();
        String[] strs = {"127.0.0.1","192.1.1.1"};
        for (String str : strs) {
            String realServer = chr.getRealServer(str);
            System.out.println(realServer);
        }

        for (String str : strs) {
            String realServer = chr.getRealServer(str);
            System.out.println(str+" -> "+realServer);
        }

        chr.deleteNode("192.168.1:1001");
        chr.addNode("192.168.1:10002");
        chr.addNode("192.168.1:10003");
        chr.addNode("192.168.1:10004");
        chr.addNode("192.168.1:10005");
        chr.addNode("192.168.1:10006");
        for (String str : strs) {
            String realServer = chr.getRealServer(str);
            System.out.println(str+" -> "+realServer);
        }

    }
}