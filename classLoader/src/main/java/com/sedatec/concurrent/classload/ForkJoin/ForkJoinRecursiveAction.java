package com.sedatec.concurrent.classload.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 *
 * 没有结果的
 **/
public class ForkJoinRecursiveAction {
    private final static int MAX_THRESHOLD = 3;
    private static AtomicInteger SUM = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CalculateRecursiveAction(0,1000));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println(SUM);
    }

    private static class CalculateRecursiveAction extends RecursiveAction{
        private  int start;
        private  int end;

        public CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) <= MAX_THRESHOLD){
               SUM.addAndGet(IntStream.rangeClosed(start,end).sum());
            }else {
                int middle = (start + end) / 2;
                CalculateRecursiveAction leftMiddle = new CalculateRecursiveAction(start,middle);
                CalculateRecursiveAction rightMiddle = new CalculateRecursiveAction(middle + 1,end);
                leftMiddle.fork();
                rightMiddle.fork();
            }
        }
    }
}
