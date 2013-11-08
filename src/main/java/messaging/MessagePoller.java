package messaging;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public abstract class MessagePoller implements Runnable {

	protected volatile boolean running = true;

	private final Queue<Message> receivedQueue = new ConcurrentLinkedQueue<Message>();

	@Override
	public void run() {
		throw new RuntimeException("Override run() in " + this);
	}

	public Message poll() {
		return receivedQueue.poll();
	}

	public void shutdown() {
		running = false;
	}

	protected void messagesReceived(Message... messages) {
		receivedQueue.addAll(Arrays.asList(messages));
	}

	protected void pause(int num, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(num);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
