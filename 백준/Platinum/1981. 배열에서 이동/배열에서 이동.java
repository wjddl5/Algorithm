import java.io.*;
import java.util.*;

public class Main {
    static int N, res=Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];

        int max = 0, min = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;

                if(min > n) min = n;
                if(max < n) max = n;
            }
        }

        binarySearch(min, max);
        System.out.println(res);
    }

    static void binarySearch(int min, int max) {
        int left = 0;
        int right = max - min;

        while(left <= right) {
            boolean chk = false;
            int mid = (left + right) / 2;

            for(int i=min; i+mid<=max; i++) {   
                if(map[0][0] < i || map[0][0] > i+mid) continue;

                if(bfs(i, i + mid)) {
                    chk = true;
                    break;
                }
            }
            
            if(chk) {
                right = mid - 1;
                res = Math.min(res, mid);
            } else {
                left = mid + 1;
            }
        }
    }

    static boolean bfs(int min, int max) {  
        Queue<int[]> queue = new ArrayDeque<>();
        resetVisit();

        queue.offer(new int[] {0, 0});
        visit[0][0] = true;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];

            if(x == N-1 && y == N-1) return true;

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny] || map[nx][ny] < min || map[nx][ny] > max) continue;
                
                queue.offer(new int[] {nx, ny});
                visit[nx][ny] = true;
            }
        }
        return false;
    }

    static void resetVisit() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visit[i][j]) visit[i][j] = false;
            }
        }
    }

}
