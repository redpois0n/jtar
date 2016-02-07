package jtar;

import java.io.DataInputStream;
import java.io.IOException;

public class Header {

	public static final int BLOCKSIZE = 512;

	private byte[] name = new byte[100];
	private byte[] mode = new byte[8];
	private byte[] uid = new byte[8];
	private byte[] gid = new byte[8];
	private byte[] size = new byte[12];
	private byte[] mtime = new byte[12];
	private byte[] chksum = new byte[8];
	private byte[] typeflag = new byte[1];
	private byte[] linkname = new byte[100];

	private byte[][] order = new byte[][] {
		name, mode, uid, gid, size, mtime, chksum, typeflag, linkname
	};

	private int offset;

	public void readRecord(DataInputStream dis) throws IOException {
		int read = 0;

		for (byte[] arr : order) {
			read += arr.length;
 			dis.readFully(arr);
		}

		int toSkip = Header.BLOCKSIZE - read;
		int skipped = 0;

		while ((skipped += dis.skipBytes(toSkip)) < toSkip);
	}

	public byte[][] getFields() {
		return order;
	}

	public String getName() {
		return new String(name).trim();
	}

	public void setName(String name) {
		this.name = Utils.makeCstr(name, 100);
	}

	public byte[] getMode() {
		return mode;
	}

	public void setMode(byte[] mode) {
		this.mode = mode;
	}

	public String getUid() {
		return new String(uid).trim();
	}

	public void setUid(byte[] uid) {
		this.uid = uid;
	}

	public String getGid() {
		return new String(gid).trim();
	}

	public void setGid(byte[] gid) {
		this.gid = gid;
	}

	public int getSize() {
		return Utils.octalToDec(new String(size));
	}

	public void setSize(int size) {
		this.size = Utils.decToOctal(size).getBytes();
	}

	public int getMtime() {
		return Utils.octalToDec(new String(mtime));
	}

	public void setMtime(int mtime) {
		this.mtime = Utils.decToOctal(mtime).getBytes();
	}

	public byte[] getChksum() {
		return chksum;
	}

	public void setChksum(byte[] chksum) {
		this.chksum = chksum;
	}

	public byte[] getTypeflag() {
		return typeflag;
	}

	public void setTypeflag(byte[] typeflag) {
		this.typeflag = typeflag;
	}

	public String getLinkname() {
		return new String(linkname).trim();
	}

	public void setLinkname(String linkname) {
		this.linkname = Utils.makeCstr(linkname, 100);
	}

	public int getDataOffset() {
		return offset;
	}

	public void setDataOffset(int dataOffset) {
		this.offset = dataOffset;
	}

	@Override
	public String toString() {
		return "Name " + getName() + "\nMode " + getMode() + "\nOwner UID " + getUid() + "\nGroup UID " + getGid() + "\nSize " + getSize() + "\nLast Modified " + getMtime() + "\nChecksum " + getChksum() + "\nLink Indicator " + getTypeflag() + "\nLinked Name " + getLinkname();
	}
}
