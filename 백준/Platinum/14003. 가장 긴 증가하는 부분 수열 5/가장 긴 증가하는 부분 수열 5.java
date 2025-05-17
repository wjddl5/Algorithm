import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] ar;
    static Node[] lis;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ar = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        lis = new Node[N];

        find();
        System.out.println(sb);
    }

    static void find() {
        lis[0] = new Node(ar[0], null);

        int length = 1;
        for(int i=1; i<N; i++) {
            int num = ar[i];

            Node node = lis[length-1];
            if(node.num < num) {
                lis[length] = new Node(num, lis[length-1]);
                length++;
            } 
            else {
                int idx = binarySearch(num, 0, length - 1);
                lis[idx] = new Node(num, idx>0 ? lis[idx-1] : null);
            }
        }
        sb.append(length).append("\n");

        LinkedList<Integer> list = new LinkedList<>();
        Node node = lis[length-1];
        while(node != null) {
            list.addFirst(node.num);
            node = node.preNode;
        }

        for(int num : list) {
            sb.append(num).append(" ");
        }
    }

    static int binarySearch(int num, int left, int right) {
        int mid;
        while(left < right) {
            mid = (left + right) / 2;
            if(num <= lis[mid].num) right = mid;
            else left = mid + 1;
        }
        return right;
    }

}

class Node {
    int num;
    Node preNode;
    Node(int num, Node preNode) {
        this.num = num;
        this.preNode = preNode;
    }
}