
public class isPalindrome {
	public static boolean isPalin(ListNode head) {
		if (head == null || head.next == null)
			return true;

		ListNode walker = head, runner = head;

		while (runner != null && runner.next != null) {
			walker = walker.next;
			runner = runner.next.next;
		}
		System.out.println(walker.val);
		
		ListNode mid = reverse(walker);
		System.out.println(mid.val);
		
		while (mid != null && head != null) {
			if (mid.val != head.val)
				return false;
		}
		return true;

	}

	public static ListNode reverse(ListNode node) {
		ListNode pre = null;

		while (node != null) {
			ListNode next = node.next;
			node.next = pre;
			pre = node;
			node = next;
		}
		return pre;
	}
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(2);
		ListNode l5 = new ListNode(1);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		
		System.out.println(isPalin(l1));
		
	}

}
