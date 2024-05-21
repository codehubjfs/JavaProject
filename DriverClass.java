/*
 * The childthread class is create to 
 */
package com.multithreading;
public class ChildThread extends Thread {

	public void run() {
		try {
			Thread.sleep(1000);
			Thread.interrupted();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {

			System.out.println("child 1: " + i);
		}
	}

}

class Child2Thread extends Thread {

	public void run() {

		for (int i = 0; i < 5; i++) {

			System.out.println("child 2 : " + i);

		}

	}
}

class Child3Thread extends Thread {

	public void run() {
		for (int i = 0; i < 5; i++) {

			System.out.println("child3 : " + i);
		}

	}
}

class Task4 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {

			System.out.println("Runnable : " + i);
		}

	}

}	
