package kr.or.ddit.thread.pooling;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 06-16
 * 톰캣은 최대 50개의 스레드를 작동시킬수 있다. 
 *
 */
public class PoolingThread {
	
	public static class PrintNumberJob implements Runnable{
		
		private int number;
		
		@Override
		public void run() {
			while(number < 10) {
				System.out.printf(" %s - %d \n",Thread.currentThread().getName(),++number);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static class GenerateJob implements Runnable{
		ThreadPoolExecutor executor;
				
		public GenerateJob(ThreadPoolExecutor executor) {
			super();
			this.executor = executor;
		}

		@Override
		public void run() {
			while(Thread.activeCount()<100) {
				executor.execute(new PrintNumberJob());
				try {
					Thread.sleep(2000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		// poolling 객체이자 실행자 .. 스레드를 따로 start시킬 필요 없다. 
		ThreadPoolExecutor executor 
		= new ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(5));
		// 동시에 active 상태가 되는 스레드 의 갯수 : 15개 ,, 시스템부하가 줄어듬
		executor.execute(new GenerateJob(executor));
		
	}
}
