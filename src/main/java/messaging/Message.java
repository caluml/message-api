package messaging;

import java.security.SecureRandom;
import java.util.Arrays;

public class Message {

	// bytes 1 -4
	private long id = generateRandomId();

	// byte 5
	private boolean encrypted = false;

	// byte 6-9
	private long timestamp = System.currentTimeMillis();

	// the rest
	private byte[] message;

	/**
	 * The raw packet, as received off the wire
	 * 
	 * @param rawMessage
	 */
	public Message(final byte[] rawMessage) {
		byte[] idBytes = new byte[8];
		byte[] encBytes = new byte[1];
		byte[] timestampBytes = new byte[8];
		byte[] messageBytes = new byte[rawMessage.length - 17];
		System.arraycopy(rawMessage, 0, idBytes, 0, 8);
		System.arraycopy(rawMessage, 8, encBytes, 0, 1);
		System.arraycopy(rawMessage, 9, timestampBytes, 0, 8);
		System.arraycopy(rawMessage, 17, messageBytes, 0, messageBytes.length);
		id = ByteUtils.toLong(idBytes);
		encrypted = ByteUtils.toBoolean(encBytes);
		timestamp = ByteUtils.toLong(timestampBytes);
		message = messageBytes;
	}

	public Message(long id, boolean encrypted, long timestamp, byte[] message) {
		this.id = id;
		this.encrypted = encrypted;
		this.timestamp = timestamp;
		this.message = message;
	}

	/**
	 * Gets the raw byte array as needed to be sent on the wire
	 * 
	 * @return
	 */
	public byte[] getRawMessage() {
		byte[] raw = new byte[message.length + 17];
		byte[] idBytes = ByteUtils.asBytes(id);
		byte[] encBytes = ByteUtils.asBytes(encrypted);
		byte[] timestampBytes = ByteUtils.asBytes(timestamp);
		System.arraycopy(idBytes, 0, raw, 0, 8);
		System.arraycopy(encBytes, 0, raw, 8, 1);
		System.arraycopy(timestampBytes, 0, raw, 9, 8);
		System.arraycopy(message, 0, raw, 17, message.length);
		return raw;
	}

	public void setMessage(final byte[] message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	private long generateRandomId() {
		final SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(System.currentTimeMillis());
		long nextLong = secureRandom.nextLong();
		if (nextLong < 0) {
			nextLong = nextLong + Long.MAX_VALUE;
		}
		return nextLong;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public byte[] getMessage() {
		return message;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	@Override
	public String toString() {
		return getClass() + " [id=" + id + ", timestamp=" + timestamp + "] ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (encrypted ? 1231 : 1237);
		result = prime * result + (int) (id ^ id >>> 32);
		result = prime * result + Arrays.hashCode(message);
		result = prime * result + (int) (timestamp ^ timestamp >>> 32);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Message other = (Message) obj;
		if (encrypted != other.encrypted) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (!Arrays.equals(message, other.message)) {
			return false;
		}
		if (timestamp != other.timestamp) {
			return false;
		}
		return true;
	}
}
