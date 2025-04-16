import java.io.*;
import java.util.*;

public class Main {
    static int target, low=0, high, answer;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int tree = Integer.parseInt(st.nextToken());
            trees[i] =  tree;
            high = Math.max(high, tree);
        } 
        find();
        System.out.println(answer);
    }

    static void find() {
        while(low <= high) {
            long sum =  0;
            int mid = (low + high) / 2;
            for(int tree :  trees) {
                if(tree > mid) sum += tree - mid;
            }
            if(sum >= target) {
                low = mid + 1;
                answer = Math.max(answer, mid);
            }
            else high = mid - 1;
        }
    }


}