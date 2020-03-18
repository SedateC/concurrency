package com.sedatec.concurrent.classload.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 *
 * 累加多个数据分组完成
 * 有返回结果
 **/
public class ForkJoinRecursiveTask {
    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculateRecursiveTask(0,1000));
        try {
            Integer integer = future.get();
            System.out.println("result :"+ integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private static class CalculateRecursiveTask extends RecursiveTask<Integer> {
        private final  int MAX_THRESHOLD = 200;
        private final  int start;
        private final  int end;

         CalculateRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
             if (end - start <= MAX_THRESHOLD ){
                 return IntStream.rangeClosed(start,end).sum();
             }else {
                 int middle = (start + end) / 2;
                 CalculateRecursiveTask leftTask = new CalculateRecursiveTask(start,middle);
                 CalculateRecursiveTask rightTask = new CalculateRecursiveTask(middle + 1,end);
                 leftTask.fork();
                 rightTask.fork();
                 return leftTask.join() + rightTask.join();
             }
        }
    }
}
