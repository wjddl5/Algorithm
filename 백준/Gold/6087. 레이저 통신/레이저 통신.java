import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer;
    static char[][] map;
    static int[][][] dist;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        dist = new int[N][M][4];

        int sx = 0, sy = 0;
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                map[i][j] = c;

                if(c == 'C') {
                    sx = i;
                    sy = j;
                }
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }

        }
        //input

        find(sx, sy);

        System.out.println(answer);
    }

    static void find(int sx, int sy) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
       
        pq.offer(new Node(sx, sy, -1, 0)); // dir: -1 == start
        dist[sx][sy][0] = 0;
        dist[sx][sy][1] = 0;
        dist[sx][sy][2] = 0;
        dist[sx][sy][3] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.dir > -1 && map[node.x][node.y] == 'C') {
                answer = dist[node.x][node.y][node.dir];
                return;
            }

            for(int k=0; k<4; k++) {
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];
                int ncost = node.dir > -1 && node.dir != k ? node.cost + 1 : node.cost;             

                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == '*' || dist[nx][ny][k] <= ncost) continue;

                pq.offer(new Node(nx, ny, k, ncost));
                dist[nx][ny][k] = ncost;
            }
        }
    }


}

class Node implements Comparable<Node>{
    int x;
    int y;
    int dir;
    int cost;

    public Node(int x, int y, int dir, int cost) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cost = cost;
    }   

    @Override
    public int compareTo(Node o1) {
        return Integer.compare(this.cost, o1.cost);
    }
}
