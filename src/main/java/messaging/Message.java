package messaging;

import java.security.SecureRandom;

public class Message {

    private long id = generateRandomId();

    private boolean encrypted = false;

    private long timestamp = System.currentTimeMillis();

    private byte[] message;

    public Message() {
    }

    public Message(final byte[] message) {
        this.message = message;
    }

    public void setMessage(final byte[] message) {
        this.message = message;
    }

    public long getId() {
        return this.id;
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
        return this.encrypted;
    }

    public void setEncrypted(final boolean encrypted) {
        this.encrypted = encrypted;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public byte[] getMessage() {
        return this.message;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message [id=" + this.id + ", encrypted=" + this.encrypted + ", timestamp="
                + this.timestamp + ", message=" + this.message + "]";
    }
}
