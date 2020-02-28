package part2.chaper8;

public class ActionContext {
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>(){
        @Override
        protected Context initialValue() {
            return new Context();//默认吧当前线程作为KEY
        }
    };

    private static class ContextHolder{
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getInstance(){
        return ContextHolder.actionContext;
    }

    public Context getContext(){
        return threadLocal.get();
    }
}
