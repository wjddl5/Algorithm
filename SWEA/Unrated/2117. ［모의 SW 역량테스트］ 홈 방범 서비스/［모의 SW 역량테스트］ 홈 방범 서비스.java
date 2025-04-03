import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int N, M, answer;
    static boolean[][] map;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            answer = 0;
            map = new boolean[N][N];
 
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    if(st.nextToken().equals("1")) map[i][j] = true;
                }
            }
 
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    for(int k=1; k<=N+1; k++) {
                        answer = Math.max(answer, service(i, j, k));
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
 
    public static int service(int x, int y, int k) {
        int cost = k*k + (k-1)*(k-1);
        int cnt = 0;
        for(int i=x-(k-1); i<x+k; i++) {
            for(int j=y-(k-1); j<y+k; j++) {
                if(i<0 || j<0 || i>=N || j>=N || Math.abs(x-i) + Math.abs(y-j) >= k) continue;
                if(map[i][j]) cnt++;
            }
        }
        if(cnt*M-cost < 0) return -1;
        return cnt;
    }
 
}