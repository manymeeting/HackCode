package others;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JosephusProblem {
    public static int get_winner(int n, int k) {
        // n is the number of people, k is the number that gets killed
        // build a linked list.
        Map<Integer,Integer> map = new HashMap<>();
        List<Node> josephus = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            Node temp = new Node(i);
            map.put(temp.val,i);
        }
        for (int i = 0; i < n; i ++) {
            if (i != n - 1) {
                josephus.get(i).next = josephus.get(i + 1);
            } else if (i != 0) {
                josephus.get(i).prev = josephus.get(i - 1);
            }
        }
        josephus.get(0).prev = josephus.get(n - 1);
        josephus.get(n - 1).next = josephus.get(0);

        int count = 0;
        Node cur = josephus.get(0);
        while (cur.next != cur) {
            count++;
            if (count != k) {
                cur = cur.next;
            } else {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                cur = cur.next;
            }
        }
        return map.get(cur.val);
    }

    public static void main(String[] args) {
        int i = 10;
        int j = 5;
        int test = get_winner(10,5);
        System.out.print(test);
    }

    private static class Node{
        Node prev;
        Node next;
        int val;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
}
