import java.io.*;
import java.util.*;

public class Main {
    static final int INF=Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static int N, M, start, end;
    static char[][] map;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N*M];
        dist = new int[N*M];
        
        for(int i=0; i<N*M; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        map = new char[N][M];
        String str;
        for(int i=0; i<N; i++) {
            str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                map[i][j] = c;
                if(c == 'S') start = i*M + j;
                if(c == 'E') end = i*M + j;
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == '#') continue;
                if(isWallOfBound(i, j)) {
                    for(int k=0; k<4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(isOutOfBound(nx, ny) || map[nx][ny]=='#') continue;
                        if(isWallOfBound(nx, ny)) {
                            graph[i*M + j].add(new Node(nx*M + ny, 0));
                        } else {
                            graph[i*M + j].add(new Node(nx*M + ny, 1));
                        }
                    }
                } else {
                    for(int k=0; k<4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(isOutOfBound(nx, ny)) continue;
                        graph[i*M + j].add(new Node(nx*M + ny, 1));
                    }    
                }
            }
        }

        find();
        System.out.println(dist[end]);
    }

    static void find() {  
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[] {start, 0});
        dist[start] = 0;

        while(!deque.isEmpty()) {
            int[] now = deque.pollFirst();
            int idx = now[0];
            int cost = now[1];

            for(Node node : graph[idx]) {
                if(dist[node.idx] > cost + node.cost) {
                    dist[node.idx] = cost + node.cost;
                    if(node.cost == 0) deque.addFirst(new int[] {node.idx, cost});
                    else deque.addLast(new int[] {node.idx, cost + 1});
                }
            }
        }
    }

    static boolean isWallOfBound(int x, int y) {
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(isOutOfBound(nx, ny)) continue;
            if(map[nx][ny] == '#') return true;
        }
        return false;
    }

    static boolean isOutOfBound(int x, int y) {
        return (x<0 || y<0 || x>=N || y>=M);
    }

}

class Node {
    int idx, cost;
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
