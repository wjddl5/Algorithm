import java.io.*;
import java.util.*;

public class Main {

    static int N, end, move;
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N+1];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            Node node = new Node(n, l, r);
            nodes[n] = node;
        }
        getEnd(1);
        find(1);
    }

    static void getEnd(int idx) {
        if(idx < 0) return;
        getEnd(nodes[idx].left);
        end = idx;
        getEnd(nodes[idx].right);
    }

    static void find(int idx) {
        if(nodes[idx].left > 0) {
            move++;
            find(nodes[idx].left);
            move++;
        }
        if(nodes[idx].right > 0) {
            move++;
            find(nodes[idx].right);
            move++;
        }
        if(idx == end) {
            System.out.println(move);
            System.exit(0);
        }
    }

}

class Node {
    int idx;
    int left;
    int right;
    Node(int idx, int left, int right) {
        this.idx = idx;
        this.left = left;
        this.right = right;
    }
}