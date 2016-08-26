package google.phone;

/*
 *  给定一个字符串，找出最多有多少个chunked palindrome,
正常的palindrome是abccba, chunked palindrome的定义是：比如volvo, 可以把vo划分在一起，(vo) (l) (vo)，
那么它是个palindrome。求实现返回最大的chunk 数量
 */
public class ValidChunkPalindrome {

	public static int chunkedPalindrome(String s) {
		
		int l = 0, r = s.length()-1;
		int preL = l, postR = r;
		int count = 0;
		while(l < r) {

			String leftChunk = s.substring(preL, l+1);
			String rightChunk = s.substring(r, postR+1);
			
			if(leftChunk.equals(rightChunk)) {
				count += 2;
				preL = l+1;
				postR = r - 1;
			}
			l++;
			r--;
		}
		return preL <= postR ? count+1 : count;
	}
	
	/*
	 *  public static int chunkedPalindrome(String s) {
        int l = 0, r = s.length() -1;
        int count = 0;
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                if (l == r) count+=1;
                else count +=2;
                l++;
                r--;
            } else {
                int pl = l, pr = r;
                while (l < r && !s.substring(pl,l+1).equals(s.substring(r,pr+1))) {
                    l++;
                    r--;
                }
                if (l < r) count+=2;
                else count+=1;
                l++;r--;
            }
        }
        return count;
    }
	 */
	public static void main(String[] args) {
		String s = "volvo";
		System.out.println(chunkedPalindrome(s));
		System.out.println(chunkedPalindrome("avolvo"));
	}
}
