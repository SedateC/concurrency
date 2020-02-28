package part2.chaper8;

public class ExecutionTask implements Runnable{
    private QueryFromDbAction queryAction = new QueryFromDbAction();
    private QueryFromHttpAction queryHttpAction = new QueryFromHttpAction();
    @Override
    public void run() {
      //  Context context = new Context();
        final Context context = ActionContext.getInstance().getContext();
      //  queryAction.execute(context);
        queryAction.execute(); //main 线程为KEY
        System.out.println("query name successful");
        queryHttpAction.execute();//main 线程为KEY
        System.out.println("The card id query successful");
        System.out.println("The name is " + context.getName() + "and cardId is " +context.getCardId());
    }
}
