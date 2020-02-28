package part2.chaper9;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/*
* change 和 save 必须成对出现
* */
public class BalkingData {
    private final String fileName;
    private String content;
    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }


    public synchronized void change(String newContent){
        this.changed = true;
    }

    public synchronized void save(){
        if (!changed){ // 发现变化已经被别人响应了 自己就放弃
            System.out.println(Thread.currentThread().getName()+"getUp this task");
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() {
        System.out.println(Thread.currentThread().getName()+" Thread call to save");
            try {
                Writer writer = new FileWriter(fileName,true);
                writer.write(content);
                writer.write("\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
