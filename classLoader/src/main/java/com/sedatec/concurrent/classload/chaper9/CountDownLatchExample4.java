package com.sedatec.concurrent.classload.chaper9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * 计数器门阀
 * 并行执行过程 线程之间相互通讯工具类
 **/
public class CountDownLatchExample4 {

    private static Random random =  new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
         Event[] events = { new Event(1),new Event(2)};
        ExecutorService executorService = Executors.newFixedThreadPool(5);
         for (Event event : events){
             List<Table> tables = capture(event);
             TaskGroup taskGroup = new TaskGroup(tables.size(),event);
             for (Table table:tables){
                 TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                 TrustSourceRecordCount SourceColumnsRunnable = new TrustSourceRecordCount(table, taskBatch);
                 TrustSourceSchemaCount SourceSchemaRunnable = new TrustSourceSchemaCount(table, taskBatch);
                 executorService.submit(SourceColumnsRunnable);
                 executorService.submit(SourceSchemaRunnable);
             }
         }
    }

    static class Event{
        int id;

        public Event(int id) {
            this.id = id;
        }
    }

    static class  Table{

        String tableName;
        long sourceRecordCount = 10;
        long targetColumnCount ;
        String SourceColumnSchema = "<table name = 'a'><column name = 'coll' type= 'varchar2'/></table>>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount ) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;

        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetColumnCount=" + targetColumnCount +
                    ", SourceColumnSchema='" + SourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture( Event event){
        List<Table> list = new ArrayList<>();
        for (int i = 0 ;i< 10;i++){
            list.add(new Table("table-" +i +"  event id :" + event.id, i*1000));
        }
        return list;
    }
    static  class  TrustSourceRecordCount implements Runnable{
        private final Table table;
        private final TaskBatch taskBatch;
        public TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            /*
            * 模拟计算时间
            * */
            try {
                Thread.sleep(random.nextInt(5_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnCount = table.sourceRecordCount;
            //System.out.println("the table " + table.tableName +" target  Record count done and update");
            taskBatch.done(table);
        }
    }


    static  class  TrustSourceSchemaCount implements Runnable{
        private final Table table;
        private final TaskBatch taskBatch;
        public TrustSourceSchemaCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            /*
             * 模拟计算时间
             * */
            try {
                Thread.sleep(random.nextInt(5_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.SourceColumnSchema;
            //System.out.println("the table " + table.tableName +" target columns capture done and update");
            taskBatch.done(table);
        }
    }


    interface Watcher {
        void startWatch();
        void done(Table table);
    }

    static class TaskBatch implements Watcher{
        private CountDownLatch countDownLatch;
        private TaskGroup taskGroup;
        public TaskBatch(int size, TaskGroup taskGroup) {
            this.countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void startWatch() {

        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0){
                System.out.println("the table finish work "+table.tableName + " ["+table+"]");
                taskGroup.done(table);//
            }
        }
    }
    /*
    * 对event 结果封装
    * */
    static class TaskGroup implements Watcher{
        private CountDownLatch countDownLatch;
        private Event event;
        public TaskGroup(int size, Event event) {
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void startWatch() {

        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            System.out.println("one event count down size :" +countDownLatch.getCount());
            if (countDownLatch.getCount() == 0){
                System.out.println("===All of table done in event :  "+ event.id);
            }
        }
    }
}
