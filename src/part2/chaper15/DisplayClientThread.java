package part2.chaper15;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/4
 **/
public class DisplayClientThread extends Thread {

    private ActiveObject activeObject;


    public DisplayClientThread(ActiveObject activeObject) {
        this.activeObject = activeObject;
    }
}
