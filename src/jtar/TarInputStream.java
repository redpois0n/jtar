package jtar;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TarInputStream extends InputStream {

	public static final int BUFFER_SIZE = 1024 * 1024;

	private DataInputStream dis;

	public TarInputStream(InputStream is) throws IOException {
		this.dis = new DataInputStream(new BufferedInputStream(is, BUFFER_SIZE));
		this.dis.mark(Integer.MAX_VALUE);
	}

	public List<Header> getHeaders() throws IOException {
		dis.reset();
		List<Header> headers = new ArrayList<Header>();

		int read = 0;
		boolean running = true;

		while (running) {
			Header h = new Header();
			h.readRecord(dis);
			read += Header.BLOCKSIZE;

			if (h.getName().length() == 0) {
				break;
			}

			h.setDataOffset(read);

			for (int i = 0; i < h.getSize(); i += Header.BLOCKSIZE) {
				long skipped = dis.skip(Header.BLOCKSIZE);

				if (skipped == -1) {
					running = false;
					break;
				} else {
					read += Header.BLOCKSIZE;
				}
			}

			headers.add(h);
		}

		return headers;
	}

	public InputStream openEntry(Header h) throws IOException {
		dis.reset();
		dis.skipBytes(h.getDataOffset());

		return dis;
	}

	public void closeEntry() throws IOException {
		dis.reset();
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		throw new IOException();
	}

	@Override
	public int read(byte[] b) throws IOException {
		throw new IOException();
	}

	@Override
	public int read() throws IOException {
		throw new IOException();
	}

	@Override
	public void close() throws IOException {
		dis.close();
	}
}
