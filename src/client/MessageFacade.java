package client;

import message.handler.Buffer;
import message.handler.Message;
import message.handler.MessageDecorator;
import message.handler.PlainMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageFacade {
    private final Buffer buffer;

    private volatile static MessageFacade instance;

    private MessageFacade(Buffer buffer){
        this.buffer = buffer;
    }

    public synchronized void add(String message){
        this.buffer.add(new MessageDecorator(new PlainMessage(message)));
    }

    public synchronized void generateRandomMessages(List<String> messages){
        Collections.shuffle(messages);
        if(!messages.isEmpty()) {
            add(messages.get(0));
        }
    }

    public synchronized static MessageFacade getInstance() {
        if(instance == null){
            instance = new MessageFacade(new Buffer());
        }
        return instance;
    }

    public synchronized List<Message> receiveMessages() {
        List<Message> messages = buffer.getBuffer();
        if(buffer.getBuffer() == null) messages = new ArrayList<>();
        buffer.empty();
        return messages;
    }
}
