package messaging;

import java.util.Collection;


public interface MessagePoller extends Runnable {

	void notifyNewMessages(Collection<Message> newMessages);

	void shutdown();
}
