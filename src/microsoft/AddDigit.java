package microsoft;

public class AddDigit {
	public static int addDigits(int num) {

		if (num >= 1 && num <= 9)
			return num;

		return getDigit(num);
	}

	private static int getDigit(int num) {

		if (num >= 1 && num <= 9)
			return num;
		int digit = 0;
		while (num > 0) {
			digit += num % 10;
			num /= 10;
		}
		return getDigit(digit);
	}
	public static void main(String[] args) {
		int d = addDigits(138);
		System.out.println(d);
	}

}
