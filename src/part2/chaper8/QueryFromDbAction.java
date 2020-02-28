package part2.chaper8;

public class QueryFromDbAction {

/*    public void execute(Context context)  {
        try {
            Thread.sleep(1000L);
            String name = "Alex Thread :"+Thread.currentThread().getName();
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/

    public void execute()  {
        try {
            Thread.sleep(1000L);
            String name = "Alex Thread :"+Thread.currentThread().getName();
            ActionContext.getInstance().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
