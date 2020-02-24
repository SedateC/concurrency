package part1.chaper4;

public class DaemoThread2 {
    public static void main(String[] args) {
        Thread innerThread = new Thread(()->{
           try {
               while (true){//永远不停
                   Thread.sleep(1_000);
                   System.out.println("innerThread thread running");
               }
           }catch (Exception e){
               e.printStackTrace();
           }finally {
               System.out.println("innerThread thread finish done");
           }
        });
        innerThread.setDaemon(true);
       // innerThread.setDaemon(true); 注释测试
        innerThread.start();
        try{
            Thread.sleep(10_000);
            System.out.println("T thread finish done");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
