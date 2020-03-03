package part2.chaper14;

public class Request {
    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public void execute(){
        System.out.println(Thread.currentThread().getName() +this);
    }

    @Override
    public String toString() {
       return  " Request execute =>  name: " + name + " Number: "+number;
    }
}
