import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count;
    static char[][] map;
    static boolean[] keys;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String str;

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0 ) {
            count = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N + 2][M + 2];
            keys = new boolean[26];
            
            for(int i=1; i<=N; i++) {
                str = br.readLine();
                for(int j=1; j<=M; j++) {
                    map[i][j] = str.charAt(j-1);
                }
            }

            str = br.readLine();
            if(!str.equals("0")) {
                for(int i=0; i<str.length(); i++) {
                    keys[str.charAt(i) - 97] = true;
                }
            }
            //
            bfs();
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs() {
        int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N + 2][M + 2];

        queue.offer(new int[] {0, 0});
        visit[0][0] = true;

        find :
        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx<0 || ny<0 || nx>N+1 || ny>M+1 || visit[nx][ny] || map[nx][ny]=='*') continue;

                if(65 <= map[nx][ny] && map[nx][ny] <= 90) {
                    if(keys[map[nx][ny] - 65]) map[nx][ny] = '.';          
                    else continue;
                }
                
                if(97 <= map[nx][ny] && map[nx][ny] <= 122) {
                    keys[map[nx][ny] - 97] = true;
                    map[nx][ny] = '.';
                    queue.clear();
                    queue.offer(new int[] {nx, ny});
                    visit = new boolean[N + 2][M + 2];
                    visit[nx][ny] = true;
                    continue find;
                }

                if(map[nx][ny] == '$') {
                    map[nx][ny] = '.';
                    count ++;
                }

                queue.offer(new int[] {nx, ny});
                visit[nx][ny] = true;
            }
        }
    }

}