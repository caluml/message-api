package messaging;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class Message {

	private long id = generateRandomId();

	private boolean encrypted = false;

	private long timestamp = System.currentTimeMillis();

	private byte[] message;

	private Map<String, Object> additionalProperties;

	public Message() {
	}

	public Message(final byte[] message) {
		this.message = message;
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

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(final boolean encrypted) {
		this.encrypted = encrypted;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public byte[] getMessage() {
		return message;
	}

	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	@JsonAnySetter
	public void setAdditionalProperties(String key, Object value) {
		if ( additionalProperties == null ) {
			additionalProperties = new HashMap<String, Object>();
		}
		additionalProperties.put(key, value);
	}

	@JsonSerialize(include = Inclusion.NON_NULL)
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	@Override
	public String toString() {
		return getClass() + " [id=" + id + ", timestamp=" + timestamp + "] ";
	}
}
