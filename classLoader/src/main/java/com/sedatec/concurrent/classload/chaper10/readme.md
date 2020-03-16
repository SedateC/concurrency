CountDownLatch vs CyclicBarrier 

1，CountDownLatch 不能 rest ,CyclicBarrier可以循环使用。  
2，CountDownLatch 工作线程互不关心 ，CyclicBarrier 必须等待所有线程到一个共同的点。  
