package messaging;

import java.util.Collection;


public interface MessageSender extends Runnable {

	void sendMessage(Message message);

	void sendMessages(Collection<Message> messages);

	void shutdown();
}
