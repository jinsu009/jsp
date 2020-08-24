package kr.or.ddit.thread;

/**
 * 06-16
 * runnable/thread API를 사용하여 , 멀티 쓰레딩 환경 운영 
 * 1. 1~10 까지의 숫자를 매 1초마다 콘솔에 출력 
 * 2. 2번의 작업을 매 3초마다 새로 시작(최대 100개의 작업을 동시 진행)
 * runnable과 thread의 차이점
 * - runnable : 가지고 있는 인터페이스가하나 run , job 
 * - thread : 최소한의 자원을 미리 할당해 둠 , jon + 자원
 * 
 * 매번 스레드가 실행되면 과부하가일어남 
 */
public class SimpleThread {

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
		@Override
		public void run() {
			while(Thread.activeCount()<100) {
				Thread th00 = new Thread(new PrintNumberJob());
				th00.start();
				try {
					Thread.sleep(2000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread( new GenerateJob()).start();
	}
	

	
// ------
//	public static void main(String[] args) throws InterruptedException {
//		Runnable r = new ThreadEx();
//		for(int i=0; i<100; i++) {
//			Thread th = new Thread(r);		
//			th.start();
//			Thread.sleep(3000);
//		}
//	}
//}
//
//class ThreadEx implements Runnable {
//	public void run() {
//		int j = 1;
//		while (j <= 100) {
//			try {
//				// 1~10 까지 출력 하기
//				for (int i = 1; i <= 10; i++) {
//					System.out.println(i);
//					Thread.sleep(1000);
//				}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			j++;
//		}
//	}
}
