package part2.chaper4;

import java.nio.Buffer;

public class ShareData {
    private final char[] buffer;

    private final ReadWriteLock readWriteLock = new ReadWriteLock();

    public ShareData(int size ) {
        this.buffer = new char[size];
        for (int i= 0; i< buffer.length ;i++){
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException{
        try{
            readWriteLock.readLock();
            return doRead();
        }finally {
            readWriteLock.readUnlock();
        }
    }

    private char[] doRead() throws InterruptedException {
        char[] newBuf = new char[buffer.length];
        for (int i = 0 ;i< buffer.length ; i++){
            newBuf[i] = buffer[i];
        }
        slowly(500);
        return newBuf;
    }


    public  void write(char c) throws InterruptedException{
        try{
            readWriteLock.writeLock();
            doWrite(c);
        }finally {
            readWriteLock.writerUnlock();
        }
    }

    private void doWrite(char c) throws InterruptedException {
        for (int i = 0 ; i < buffer.length; i++){
            buffer[i] = c;
            slowly(50);
        }
        System.out.println(Thread.currentThread().getName() + " write "
                +String.valueOf(c));
    }


    private void slowly(int ms) throws InterruptedException {
        Thread.sleep(ms);
    }
}
