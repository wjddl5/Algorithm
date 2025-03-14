import java.io.*;
import java.util.*;

public class Solution {

    static StringBuilder sb = new StringBuilder();
    static Node[] rootAr;
    static int[][] nodeAr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int tc=1; tc<11; tc++) {
            sb.append("#").append(tc).append(" ");

            int n = Integer.parseInt(br.readLine());

            rootAr = new Node[n+1];
            nodeAr = new int[n+1][2];

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());

                int root = Integer.parseInt(st.nextToken());
                char value = st.nextToken().charAt(0);
                rootAr[root] = new Node(value);

                int k = 0;
                while(st.hasMoreTokens()) {
                    nodeAr[root][k] = Integer.parseInt(st.nextToken());
                    k++;
                }
            }

            for(int i=1; i<+n; i++) {
                int left = nodeAr[i][0];
                if(left>0) rootAr[i].left = rootAr[left];

                int right = nodeAr[i][1];
                if(right>0) rootAr[i].right = rootAr[right];
            }

            inorder(rootAr[1]);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        sb.append(node.root);
        inorder(node.right);
    }

}

class Node {
    public char root;
    public Node left;
    public Node right;

    public Node(char root) {
        this.root = root;
        this.left = null;
        this.right = null;
    }
}
