package tarlib;

import java.util.Arrays;

public class Utils {

	public static byte[] makeCstr(String s, int len) {
		byte[] b = s.getBytes();
		byte[] b1 = Arrays.copyOf(b, len);

		return b1;
	}

	public static int octalToDec(String octal) {
		String s = "";

		for (int i = 0; i < octal.length(); i++) {
			if (octal.charAt(i) != '0') {
				s += octal.substring(i, octal.length()).trim();
				break;
			}
		}

		return Integer.parseInt(s, 8);
	}

	public static String decToOctal(int dec) {
		return Integer.toOctalString(dec) + '\0';
	}

}
