import java.io.*;
import java.util.*;

public class Main {

    static int N, M, minTime = Integer.MAX_VALUE, emptySpace;
    static int[][] map;
    static Virus[] active;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        active = new Virus[M];

        ArrayList<Virus> list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());     
                switch(n) {
                    case 0:
                        emptySpace++;
                        break;
                    case 1:
                        map[i][j] = 1;
                        break;
                    case 2:
                        map[i][j] = 2;
                        list.add(new Virus(i, j, 0));
                        break;
                }
            }
        }

        int size = list.size();
        Virus[] viruses = new Virus[size];
        list.toArray(viruses);

        if(emptySpace == 0) {
            System.out.println(0);
        } else {
            setGroup(viruses, size, 0, 0);
            System.out.println(minTime < Integer.MAX_VALUE ? minTime : -1);
        }
    }

    static void setGroup(Virus[] viruses, int size, int depth, int start) {
        if(depth == M) {
            bfs();
            return;
        }
        for(int i=start; i<size; i++) {
            active[depth] = viruses[i];
            setGroup(viruses, size, depth+1, i+1); 
        }
    }

    static void bfs() {
        Queue<Virus> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        int cnt = emptySpace;

        for(Virus v : active) {
            queue.offer(v);
            visit[v.x][v.y] = true;
        }

        while(!queue.isEmpty()) {
            Virus virus = queue.poll();
            int x = virus.x;
            int y = virus.y;
            int t = virus.time;
            
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny] || map[nx][ny] == 1) continue;
                
                queue.offer(new Virus(nx, ny, t+1));
                visit[nx][ny] = true;
                if(map[nx][ny] == 0) cnt--;

                if(cnt == 0) {
                    minTime = Math.min(minTime, t+1);
                    return;
                }
                
            }
        }
    }

}

class Virus {
    int x, y, time;
    Virus(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}