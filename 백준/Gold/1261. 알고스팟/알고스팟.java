import java.io.*;
import java.util.*;

public class Main {

    static int N, M, count;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        
        String str;
        for(int i=0; i<N; i++) {
            str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j) == '0' ? 0 : 1;
            }
            
        }

        find();
        System.out.println(count);
    }

    static void find() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[][] visit = new boolean[N][M];

        queue.offer(new Node(0, 0, 0));
        visit[0][0] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            int x = node.x;
            int y = node.y;
            int cnt = node.cnt;

            if(x == N-1 && y == M-1) {
                count = cnt;
                return;
            }

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny]) continue;

                if(map[nx][ny] == 0) {
                    queue.offer(new Node(nx, ny, cnt));
                } else {
                    queue.offer(new Node(nx, ny, cnt + 1));
                }
                visit[nx][ny] = true;
            }
        }
    }

}

class Node implements Comparable<Node>{
    int x;
    int y;
    int cnt;
    Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
    @Override
    public int compareTo(Node o) {
        return this.cnt - o.cnt;
    }
}