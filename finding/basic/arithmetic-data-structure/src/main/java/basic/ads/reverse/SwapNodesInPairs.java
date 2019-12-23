package basic.ads.reverse;

/**
 * SwapNodesInPairs 节点两两交换
 *
 * @author yakir on 2019/12/03 14:14.
 */
public class SwapNodesInPairs {

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

    /**
     * 两两交换链表中节点的位置
     *
     * @param head 链表
     * @return 当前
     */
    public ListNode swapPairs(ListNode head) {

        if (null == head || null == head.next) return head;
        ListNode next = head.next;
        head.next = swapPairs(head.next.next);
        next.next = head;
        return next;
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

        ListNode one = ListNode.create(1);

        SwapNodesInPairs inPairs = new SwapNodesInPairs();
        inPairs.linkedList(one, 10, 2);
        System.out.println(one.toString());

        ListNode node = inPairs.swapPairs(one);
        System.out.println(node.toString());

    }
}
