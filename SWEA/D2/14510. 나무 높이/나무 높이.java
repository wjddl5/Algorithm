
import java.io.*;
import java.util.*;

public class Solution {
    static int N, max, one, two, answer;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            max = one = two = answer = 0;
            N = Integer.parseInt(br.readLine());
            trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                int tree = Integer.parseInt(st.nextToken());
                trees[i] = tree;
                max = Math.max(max, tree);
            }

            for(int t : trees) {
                one += (max-t)%2;
                two += (max-t)/2;
            }
            while(one + 2 <= two) {
                one += 2;
                two -= 1;
            }
            if(one > two) answer = one * 2 -1;
            else answer = two * 2;
            sb.append("#"+tc+" ").append(answer).append("\n");
        }
        System.out.println(sb);
    } 
    
}
