package chaper10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements Lock {
    //The initValue is true indicated the lock have be get
    //The initValue is false indicated the lock is free (other thrad can get This)
    private Boolean initValue;
    private Thread currentThread;
    private Collection<Thread> blockThreadCollection = new ArrayList<>();

    public BooleanLock() { //默认false
        this.initValue = false;
    }

    @Override
    public synchronized void  lock() throws InterruptedException {
        while (initValue){
            blockThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        this.initValue = true;
        this.currentThread = Thread.currentThread();
        blockThreadCollection.remove(Thread.currentThread());
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) return;;
        long hasRemaining = mills; //当前剩余等待时间
        long endTime  = System.currentTimeMillis() + mills;
        while (initValue){
            if (hasRemaining <0 ) throw new TimeOutException("Time Out");
            blockThreadCollection.add(Thread.currentThread());
            this.wait(mills - 10l); //等待mills - 50 后自动唤醒
            hasRemaining  = endTime - System.currentTimeMillis() ;
        }
        initValue = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void  unlock() {
        if (Thread.currentThread() == currentThread){
            this.initValue = false;
            System.out.println(Thread.currentThread().getName()+"release the lock monitor");
            this.notifyAll();
        }
    }

    @Override
    public synchronized Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockThreadCollection.size();
    }
}
