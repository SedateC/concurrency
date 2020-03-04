package part2.chaper15;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/4
 * 对应ActiveObject的每一个方法
 **/
public abstract class MethodRequest {
    protected final Servant servant;
    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
