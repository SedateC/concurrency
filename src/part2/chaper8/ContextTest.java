package part2.chaper8;

import java.util.stream.IntStream;

public class ContextTest {
    public static void main(String[] args) {
        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(new ExecutionTask()).start();
        });
    }
}
