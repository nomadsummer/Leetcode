package microsoft;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}

public class PalindromLinkedList {

	public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next != null && runner.next.next != null ) {
            walker = walker.next;
            runner = runner.next.next;
        }
        
        walker = reverse(walker);
        
        while(walker.next != null) {
            if(walker.val != head.val){
                return false;
            }
            walker = walker.next;
            head = head.next;
        }
        return true;
    }
    
    private static ListNode reverse(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        
        //ListNode pre = new ListNode(-1) will cause problem when (0,0)
        ListNode pre = null;
        ListNode curr = head;
        
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre; 
    }
    
    public static void main(String[] args) {
    	
    	ListNode l1 = new ListNode(0);
    	ListNode l2 = new ListNode(0);
    	l1.next = l2;
    	
    	boolean b = isPalindrome(l1);
    	System.out.println(b);
    }
}


