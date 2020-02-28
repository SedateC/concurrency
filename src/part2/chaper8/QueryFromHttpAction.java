package part2.chaper8;

public class QueryFromHttpAction {

 /*   public void execute(Context context)  {
        try {
        String name =  context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
        Thread.sleep(1001L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } */

    public void execute()  {
        Context context = ActionContext.getInstance().getContext();
        try {
        String name =  context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
        Thread.sleep(1001L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getCardId(String name){
        try {
            Thread.sleep(1001L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "452701119900232451"+Thread.currentThread().getName();
    }
}
