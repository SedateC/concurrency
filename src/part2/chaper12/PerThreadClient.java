package part2.chaper12;


import java.util.stream.IntStream;

public class PerThreadClient {
    public static void main(String[] args) {
        MessageHandler messageHandler = new MessageHandler();
        IntStream.rangeClosed(0,10).forEach(i->{
            messageHandler.request(new Message("message:"+String.valueOf(i)));
        });
        messageHandler.shutdown();
    }
}
