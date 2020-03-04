package part2.chaper15;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/4
 **/
public class DisplayClientThread extends Thread {

    private ActiveObject activeObject;


    public DisplayClientThread(String name ,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0 ;true;i++){
            String text = Thread.currentThread().getName()+ "=>" +i;
            activeObject.displayString(text);
            Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
