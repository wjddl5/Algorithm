import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static class Node {
        public char value;
        public Node left;
        public Node right;

        public Node(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
   
    static void preorder(Node node) {
        if(node == null) return;
        sb.append(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        sb.append(node.value);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.value);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        Node[] rootAr = new Node[n];
        char[][] nodeAr = new char[n][2];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int j = c - 65;
            rootAr[j] = new Node(c);

            nodeAr[j][0] = st.nextToken().charAt(0);
            nodeAr[j][1] = st.nextToken().charAt(0);

        }

        int i = 0;
        for(char[] nodes : nodeAr) {
            char c =nodes[0];
            if(c!='.') rootAr[i].left = rootAr[c - 65];

            char c2 =nodes[1];
            if(c2!='.') rootAr[i].right = rootAr[c2 - 65];
            
            i++;
        }

        preorder(rootAr[0]);
        sb.append('\n');
        inorder(rootAr[0]);
        sb.append('\n');
        postorder(rootAr[0]);

        System.out.println(sb);
    }
    
}
    