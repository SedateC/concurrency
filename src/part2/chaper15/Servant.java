package part2.chaper15;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/4
 **/
 class Servant implements ActiveObject {
    @Override
    public Result makeString(int count, char fillChar) {
        char [] buf = new char[count];
        for (int i = 0; i<count;i++){
            buf[i] = fillChar;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult(String.valueOf(buf));
    }

    @Override
    public void displayString(java.lang.String text) {
        try{
            System.out.println("Display :"+text);
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
