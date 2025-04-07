import java.io.*;
import java.util.*;

public class Solution {

    static int N, W, answer;
    static int[] price, weight;
    static int[][] ar;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            price = new int[N+1];
            weight = new int[N+1];
            ar = new int[N+1][W+1];

            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                weight[i] = Integer.parseInt(st.nextToken());
                price[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=N; i++) {
                for(int w=1; w<=W; w++) {
                    if(weight[i] <= w) {
                        ar[i][w] = Math.max(ar[i-1][w], ar[i-1][w-weight[i]] + price[i]);
                    }
                    else {
                        ar[i][w] = ar[i-1][w];
                    }
                }
            }
            answer = ar[N][W];
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

}