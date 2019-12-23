package basic.ads.reverse;

/**
 * LinkedListReverse
 *
 * @author yakir on 2019/12/03 20:03.
 */
public class ReverseLinkedList {

    private static class ListNode {

        int      val;
        ListNode next;

        private ListNode(int val) {
            this.val = val;
        }

        public static ListNode create(int val) {
            return new ListNode(val);
        }

        @Override
        public String toString() {
            return "{val: " + val + "} " + (null != next ? next.toString() : "");
        }
    }

    public ListNode reverseList(ListNode head) {

        if (null == head || null == head.next) return head;

        ListNode tmp = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return tmp;

    }

    /**
     * 构建链表
     *
     * @param head 链表头
     * @param i    链表节点数
     * @param pos  当前节点位置
     * @return 交换节点之后的链表头
     */
    public ListNode linkedList(ListNode head, int i, int pos) {

        head.next = ListNode.create(pos);

        if (i == pos) return head;
        return linkedList(head.next, i, pos + 1);
    }

    public static void main(String[] args) {

        ListNode          root      = ListNode.create(1);
        ReverseLinkedList reversell = new ReverseLinkedList();
        reversell.linkedList(root, 10, 2);
        ListNode head = reversell.reverseList(root);
        System.out.println(head);
    }

}
