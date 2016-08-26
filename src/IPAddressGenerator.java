import java.util.ArrayList;
import java.util.List;

public class IPAddressGenerator {
	// General ideal is using backtracking to generate all possible permutation
	// of input string
	// However, we should apply some limitations of when by checking if the
	// generated IP address is valid or not.
	// The IP address should be consists of 4 parts, each between 0~255, IP
	// address cannot start with 0;

	/**
	 * 
	 * @param input
	 *            the String to parse to generate IP address
	 * @return List of possible IP addresses
	 */
	public List<String> generateIPAddress(String input) {
		List<String> rst = new ArrayList<String>();

		// corner case, invalid input
		if (input == null || input.length() == 0 || input.length() < 4 || input.length() > 12) {
			// throw InvalidParameterException or so, here for simplicity, I
			// just return an empty list
			return rst;
		}
		IPAddressGenerator ipGenerator = new IPAddressGenerator();
		ipGenerator.backtracking(rst, input, 3, "");
		return rst;
	}

	/**
	 * 
	 * @param rst
	 *            generated list of valid Ip address
	 * @param s
	 *            the input string
	 * @param dot
	 *            number of dot left to put in an Ip address
	 * @param ip
	 *            current being processed ip address
	 */
	private void backtracking(List<String> rst, String s, int dot, String ip) {

		if (dot == 0) {
			if (isValid(s)) {// done generating one valid ip address
				ip += s;
				rst.add(ip.toString());
			}
			return;
		}
		for (int i = 1; i < 4 && i < s.length(); i++) {
			String pre = s.substring(0, i);

			if (!isValid(pre)) {
				backtracking(rst, s.substring(i), dot - 1, ip + pre + ".");
			}
		}
	}

	/**
	 * Check if each part of generated Ip address is valid or not
	 * 
	 * @param s
	 * @return true if the part of Ip address is valid
	 */
	public boolean isValid(String s) {
		if (s.startsWith("0") && s.length() > 1 || Integer.valueOf(s) > 255)
			return false;

		return true;
	}

	public static void main(String[] args) {
		IPAddressGenerator ipGenerator = new IPAddressGenerator();

		String input1 = "12345";
		List<String> rst = ipGenerator.generateIPAddress(input1);
		for (String s : rst) {
			System.out.println(s);
		}
	}

}
