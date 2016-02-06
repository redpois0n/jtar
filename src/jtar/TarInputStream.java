package jtar;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TarInputStream extends InputStream {

	private DataInputStream dis;

	public TarInputStream(InputStream is) throws IOException {
		this.dis = new DataInputStream(is);
	}

	public List<Header> getHeaders() throws IOException {
		List<Header> headers = new ArrayList<Header>();

		boolean running = true;
		while (running) {
			Header h = new Header();
			h.readRecord(dis);

			if (h.getName().length() == 0) {
				break;
			}

			for (int i = 0; i < h.getSize(); i += 512) {
				long skipped = dis.skip(512);

				if (skipped == -1) {
					running = false;
					break;
				}
			}

			headers.add(h);
		}

		return headers;
	}

	@Override
	public int read(byte b[], int off, int len) throws IOException {
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
}
