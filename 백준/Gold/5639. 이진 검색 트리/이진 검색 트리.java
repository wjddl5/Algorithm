import java.io.*;
import java.util.*;

public class Main {

    static Node root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        root = new Node(Integer.parseInt(br.readLine()));
        while(true) {
            String str = br.readLine();
            if(str == null) break;

            int n = Integer.parseInt(str);

            addTree(n);
        }

        postOrder(root);
        System.out.println(sb);
    }

    static void postOrder(Node node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.idx).append('\n');
    }

    static void addTree(int n) {
        Node node = new Node(n);

        Node r = root;

        while(true) {
            if(n < r.idx) {
                if(r.left == null) {
                    r.left = node;
                    break;
                }
                r = r.left;
            } else {
                if(r.right == null) {
                    r.right = node;
                    break;
                }
                r = r.right;
            }
        }
    }

}

class Node {
    int idx;
    Node left, right;
    Node(int idx) {
        this.idx = idx;
    }
}