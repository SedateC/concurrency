package part2.chaper7;

public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return "Alex";
        }
    };
    public static void main(String[] args) {
      //  threadLocal.set("dsadasd");
       System.out.println( threadLocal.get());
    }
}
