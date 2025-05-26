import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X, Y;
    static char[][] map;
    static int[] dx={-1,0,1,0},dy={0,1,0,-1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            
            map = new char[N][M];
            
            ArrayList<int[]> fires = new ArrayList<>();
            for(int i=0; i<N; i++) {
                String str = br.readLine();
                for(int j=0; j<M; j++) {
                    char c = str.charAt(j);
                    map[i][j] = c;
                    if(c == '@') {
                        X = i;
                        Y = j;
                    }
                    if(c == '*') {
                        fires.add(new int[] {i, j});
                    }
                }
            }
            find(fires);
        }
        System.out.println(sb);
    }

     static void find(ArrayList<int[]> fires) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        for(int[] fire : fires) {
            queue.offer(new int[] {1, fire[0], fire[1]});
        }

        queue.offer(new int[] {2, X, Y, 0});

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int type = tmp[0];

            if(type == 1) { //fire
                int x = tmp[1];
                int y = tmp[2];

                for(int k=0; k<4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                
                    if(map[nx][ny]=='#' || map[nx][ny]=='*') continue;
                    
                    map[nx][ny] = '*';
                    queue.offer(new int[] {1, nx, ny});
                }

            } else {
                int x = tmp[1];
                int y = tmp[2];
                int time = tmp[3];

                for(int k=0; k<4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if(nx<0 || ny<0 || nx>=N || ny>=M) {
                        sb.append(time+1).append("\n");
                        return;
                    }
                    if(map[nx][ny]=='.' && !visit[nx][ny]) {
                        queue.offer(new int[] {2, nx, ny, time+1});      
                        visit[nx][ny] = true;
                    }
                }
            }
        }
        sb.append("IMPOSSIBLE").append("\n");
    }

}