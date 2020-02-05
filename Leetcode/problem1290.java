/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int decimal = 0;
        
        ListNode n = head;
        int step = 0;
        
        while(n != null) {
            decimal *= 2;
            decimal += n.val;
            n = n.next;
        }
        
        return decimal;
    }
}
