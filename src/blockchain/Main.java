package blockchain;

import client.Client;
import constants.Block;
import constants.Thread;
import mining.Miner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1;
        try {
            List<java.lang.Thread> clientThreads = new ArrayList<>();
            List<java.lang.Thread> minerThreads = new ArrayList<>();
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            while (numberOfThreads < Thread.CAPACITY.value) {
                Miner miner = new Miner(numberOfThreads);
                Client client = new Client();
                java.lang.Thread clientThread = new java.lang.Thread(client, "Client: " + numberOfThreads);
                java.lang.Thread minerThread = new java.lang.Thread(miner, "Miner: " + numberOfThreads);
                clientThread.start();
                minerThread.start();
                clientThreads.add(clientThread);
                minerThreads.add(minerThread);
                numberOfThreads++;
            }
            for(java.lang.Thread thread : minerThreads){
                thread.join();
            }
            for(java.lang.Thread thread : clientThreads){
                thread.join();
            }
            System.out.println(blockChain);
        } catch (Exception e) {
            System.out.println("Exception occurred: " +  e.getMessage());
            e.printStackTrace();
        }
    }
}
