package algorithms.greedy;

import java.util.PriorityQueue;

public class HuffmanCoding {

    class Node {
        int frequency;
        char data;
        Node left, right;

        Node(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }
    }

    private void printCodes(Node root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.data)) {
            System.out.println(root.data + ": " + s);
            return;
        }
        printCodes(root.left, s + "0");
        printCodes(root.right, s + "1");
    }

    public void huffmanCoding(char[] charArray, int[] charFreq) {
        int n = charArray.length;
        PriorityQueue<Node> q = new PriorityQueue<>(n, (l, r) -> l.frequency - r.frequency);

        for (int i = 0; i < n; i++) {
            q.add(new Node(charArray[i], charFreq[i]));
        }

        while (q.size() > 1) {
            Node left = q.poll();
            Node right = q.poll();
            Node newNode = new Node('-', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            q.add(newNode);
        }

        printCodes(q.poll(), "");
    }
}
