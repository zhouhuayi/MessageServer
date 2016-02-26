package com.choose.test;

import java.util.concurrent.ArrayBlockingQueue;

public class mainTest {
	private int queueSize = 10;
	private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);

	public static void main(String[] args) {
//		mainTest test = new mainTest();
//		Producer producer = test.new Producer();
//		Consumer consumer = test.new Consumer();
//
//		producer.start();
//		consumer.start();
		
		/*次数与胜场的比例为2:35*/
		int win1 = 15;
		int fal1 = 13;
		
		int sorce1 = (win1-fal1)*35 + (win1+fal1)*2;
		
		int win2 = 40;
		int fal2 = 42;
		
		int sorce2 = (win2-fal2)*35 + (win2+fal2)*2;
		
		System.out.println(sorce1);
		System.out.println(sorce2);
	}

	class Consumer extends Thread {

		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while (true) {
				try {
					queue.take();
					System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Producer extends Thread {

		@Override
		public void run() {
			produce();
		}

		private void produce() {
			while (true) {
				try {
					queue.put(1);
					System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
