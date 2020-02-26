package part2.chaper4;

/*
* 读写分离锁
* */
public class ReadWriteLock {
   private int  readingReaders = 0;
   private int  waitingReaders = 0;
   private int  writingWriters = 0;
   private int  waitingWriters = 0;
   private boolean preferWriter = true;

   ReadWriteLock(){
      this(true);
   }

   public ReadWriteLock(boolean preferWriter) {
      this.preferWriter = preferWriter;
   }

   public synchronized void readLock() throws InterruptedException {
      this.waitingReaders ++;
      try{
         while (writingWriters >0 || (waitingWriters > 0 && preferWriter)){
            this.wait();
         }
         readingReaders++;
      }finally {
         this.waitingReaders --;
      }
   }

   public synchronized void readUnlock(){
      this.readingReaders --;
      this.notifyAll();
   }

   public synchronized void writeLock() throws InterruptedException {
      this.waitingWriters++;
      try {
         while (readingReaders > 0 || writingWriters >0){
            this.wait();
         }
         this.writingWriters++;
      }finally {
         this.waitingWriters--;
      }
   }
   public synchronized void writerUnlock(){
      this.writingWriters --;
      this.notifyAll();
   }
}
