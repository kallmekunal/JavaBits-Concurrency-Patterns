package concurrency_patterns.active_object;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadSafeCounter implements Counter {
	
	BlockingQueue<Callable<Long>> callableTasks = new LinkedBlockingQueue<Callable<Long>> ();
	BlockingQueue<Long> blockingQueue = new LinkedBlockingQueue<Long>();
	Long longValue;
	
	public ThreadSafeCounter(Long longValue) {
		this.longValue = longValue;
		new Thread(new Runnable() {
			
			
			public void run() {
				ExecutorService executor = Executors.newSingleThreadExecutor();
				try {
					
					while(true) {
					System.out.println("Eating cpu");	
						try {
							blockingQueue.put( executor.submit(callableTasks.take()).get());
				//	Thread.sleep(1l);
					/*if(future.isDone())
					{
						blockingQueue.put(future.get());
					}else {
						System.out.println("Not complete");;
					}*/
						} catch (InterruptedException | ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				finally {
					executor.shutdown();
				}
			}
		}).start();
	}
	
	public long get() {
		return 0;
	}

	public long incrementAndGet() {
		return addTask(new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				// TODO Auto-generated method stub
				return ++longValue;
			}
		});	}

	public long getAndIncrement() {
		return addTask(new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				// TODO Auto-generated method stub
				return longValue++;
			}
		});	}

	public long decrementAndGet() {
		return addTask(new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				// TODO Auto-generated method stub
				return --longValue;
			}
		});	}

	public long getAndDecrement() {
		return addTask(new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				// TODO Auto-generated method stub
				return longValue--;
			}
		});	}
	
	private Long addTask(Callable<Long> callable) {
		Long longResult = null;
		try {
			callableTasks.put(callable);
			while(true) {
				longResult = blockingQueue.poll(500, TimeUnit.MILLISECONDS);
				if(longResult != null) {
					System.out.println("Result "+ longResult.longValue());
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return longResult;
		
	}

}
