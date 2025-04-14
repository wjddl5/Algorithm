import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] answer = new int[n][m];
        int[][] map = new int[n][m];
        int[] start = {-1, -1};
        for(int i=0; i<n; i++) {
            Arrays.fill(answer[i], -1);
        }
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                int k = Integer.parseInt(st.nextToken());
                map[i][j] = k;
                if(k==2) {
                    start[0] = i;
                    start[1] = j;
                } 
                if(k==0) {
                    answer[i][j] = k;
                }
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        int[] dx = {-1,0,1,0}, dy={0,1,0,-1};
        q.offer(new int[] {start[0], start[1], 0});
        v[start[0]][start[1]] = true;
        answer[start[0]][start[1]] = 0;
        while(!q.isEmpty()) {
            int[] t = q.poll();

            for(int k=0; k<4; k++) {
                int nx = t[0] + dx[k];
                int ny = t[1] + dy[k];
                if(nx<0 || ny<0 || nx>=n || ny>=m || v[nx][ny] || map[nx][ny]==0) continue;

                q.offer(new int[] {nx, ny, t[2]+1});
                v[nx][ny] = true;
                answer[nx][ny] = t[2] + 1;
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

