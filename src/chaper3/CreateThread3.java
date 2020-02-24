package chaper3;

public class CreateThread3 {
    private static int counter = 0;
    private static int index  = 0;
    public static void main(String[] args) {
        try {
            add(index);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(counter);
        }finally {
            System.out.println(counter);
        }
    }
    /*
    *超出栈显示， counter 为栈深度
    * */
    private static void  add(int i){
        counter ++;
        add(i++);

    }

}
