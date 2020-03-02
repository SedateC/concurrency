package part2.chaper13;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/2
 **/
public class CounterTest {
    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();
        Thread.sleep(3_000);
        counterIncrement.close();
    }
}
