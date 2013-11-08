package messaging;

import java.nio.ByteBuffer;

public class ByteUtils {

	/**
	 * Converts a long to a byte[]
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] asBytes(long value) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(value);
		return buffer.array();
	}

	/**
	 * Converts a byte[] to a long
	 * 
	 * @param bytes
	 * @return
	 */
	public static long toLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(bytes);
		buffer.flip();// need flip
		return buffer.getLong();
	}

	/**
	 * Concatenates the first and second byte arrays together
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static byte[] concat(byte[] first, byte[] second) {
		byte[] res = new byte[first.length + second.length];
		System.arraycopy(first, 0, res, 0, first.length);
		System.arraycopy(second, 0, res, first.length, second.length);
		return res;
	}

	public static boolean toBoolean(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(1);
		buffer.put(bytes);
		buffer.flip();// need flip ?
		return buffer.get() == 1;
	}

	public static byte[] asBytes(boolean bool) {
		ByteBuffer buffer = ByteBuffer.allocate(1);
		if (bool) {
			buffer.put((byte) 1);
		} else {
			buffer.put((byte) 0);
		}
		return buffer.array();
	}
}
