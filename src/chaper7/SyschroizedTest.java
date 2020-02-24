package chaper7;

public class SyschroizedTest {
    public static void main(String[] args) {
        new Thread("t1"){
            @Override
            public void run() {
                SychroziedStatic.m1();
            }
        }.start();
        new Thread("t2"){
            @Override
            public void run() {
                SychroziedStatic.m2();
            }
        }.start();
    }
}
