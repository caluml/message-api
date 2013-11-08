package messaging;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;


public abstract class MessageSender implements Runnable {

	protected volatile boolean running = true;

	protected final Queue<Message> outboundQueue = new ConcurrentLinkedQueue<Message>();

	@Override
	public void run() {
		throw new RuntimeException("Override run() in " + this);
	}

	protected void sendMessages(Message... messages) {
		outboundQueue.addAll(Arrays.asList(messages));
	}

	public void shutdown() {
		running = false;
	}

	protected void pause(int num, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(num);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
