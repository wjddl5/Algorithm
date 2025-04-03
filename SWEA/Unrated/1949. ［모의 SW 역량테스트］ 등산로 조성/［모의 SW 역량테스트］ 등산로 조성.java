import java.io.*;
import java.util.*;
 
public class Solution {
     
    static int N, K, start, answer;
    static int[][] map;
    static boolean check=true;
    static boolean[][] v;
    static int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        for(int tc=1; tc<=T; tc++) {
            answer = 0;
            start = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];    
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    map[i][j] = n;
                    if(start < n) start = n;
                }
            }
            setStart();
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
 
    public static void setStart() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] < start) continue;
                v = new boolean[N][N];
                dfs(i, j, 1);
            }
        }
    }
 
    public static void dfs(int x, int y, int depth) {
        v[x][y] = true;
        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
 
            if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny]) continue;
 
            if(map[x][y] > map[nx][ny]) dfs(nx, ny, depth+1);
            else if(check){
                for(int k=1; k<=K; k++) {
                    map[nx][ny] -= k;
                    check = false;
                    if(map[x][y] > map[nx][ny]) dfs(nx, ny, depth+1);
                    map[nx][ny] += k;
                    check = true;
                }
            }
        }
        v[x][y] = false;
        answer = Math.max(answer, depth);
    }
 
}