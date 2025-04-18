
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int count = 0;
            int N = Integer.parseInt(br.readLine());
            int[] child = new int[N];
            int[] ar = new int[N + 1];
            boolean[] visit = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                child[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=0; i<N; i++) {
                if(visit[child[i] -1]) ar[child[i]] = ar[child[i] -1] + 1; 
                else ar[child[i]] = 1;
                visit[child[i]] = true;
            }
            for(int n : ar) {
                count = Math.max(count, n);
            }
            sb.append("#"+tc+" ").append(N - count).append("\n");
        }
        System.out.println(sb);
    } 
    
}
