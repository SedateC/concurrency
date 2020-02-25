package part2.chaper3;

import java.util.Arrays;
import java.util.stream.Stream;

public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
