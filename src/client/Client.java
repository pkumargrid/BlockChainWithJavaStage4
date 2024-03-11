package client;

import blockchain.BlockChain;
import constants.Block;
import datafeeder.Initializer;
import model.RandomMessage;
import java.io.IOException;

public class Client implements Runnable{

    public Client() {

    }

    public void polling() throws IOException, ClassNotFoundException {
        BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
        MessageFacade messageFacade = MessageFacade.getInstance();
        while(blockChain.getBlockChainSize() < Block.CAPACITY.value){
            RandomMessage randomMessage = Initializer.initialize();
            messageFacade.generateRandomMessages(randomMessage.getListOfMessage());
        }
    }

    @Override
    public void run() {
        try {
            polling();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
