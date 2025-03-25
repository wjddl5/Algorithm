import java.io.*;
import java.util.*;

class Bridge{
    public int from;
    public int to;
    public int size;

    public Bridge(int from, int to, int size) {
        this.from = from;
        this.to = to;
        this.size = size;
    }
}

public class Main {

    static int[][] map;
    static boolean[][] v;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1}, island;
    static int N, M, answer;
    static ArrayList<Bridge> bridgeList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        v = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());;
            }
        }
        br.close();

        set();
        getAnswer();
        System.out.println(answer>0 ? answer : -1);
    }

    public static void getAnswer() {
        for(Bridge b : bridgeList) {
            int from = b.from;
            int to = b.to;
            if(find(from) == find(to)) continue;

            union(b.from, b.to);
            answer += b.size;
        }

        for(int node : island) {
            if(node == 0) continue;
            if(find(node) != 1) answer = -1;
        }
    }

    public static void set() {
        int islandNum = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(v[i][j]) continue;

                if(map[i][j] == 1) {
                    makeIsland(i, j, islandNum);
                    islandNum++;
                }
            }
        }

        island= new int[islandNum];
        for(int i=0; i<islandNum; i++) {
            island[i] = i;
        }
        for(int num=1; num<=islandNum; num++) {
            makeBridge(findIsland(num), num);
        }
        bridgeList.sort(Comparator.comparing(Bridge -> Bridge.size));
    }

    public static void makeIsland(int x, int y, int islandNum) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {x, y});
        v[x][y] = true;
        map[x][y] = islandNum;

        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            for(int k=0; k<4; k++) {
                int nx = p[0] + dx[k];
                int ny = p[1] + dy[k]; 

                if(outOfBound(nx, ny) || v[nx][ny] || map[nx][ny]==0) continue;

                queue.offer(new int[] {nx, ny});
                v[nx][ny] = true;
                map[nx][ny] = islandNum;
            }
        }
    }

    public static ArrayList<int[]> findIsland(int islandNum) {
        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == islandNum) list.add(new int[] {i, j});
            }
        }
        return list;
    }

    public static void makeBridge(ArrayList<int[]> list, int islandNum) {
        for(int[] ar : list) {
            for(int k=0; k<4; k++) {
                int bridgeLength = 0;
                int kk = 1;
                while(true) {
                    int nx = ar[0] + (dx[k] * kk);
                    int ny = ar[1] + (dy[k] * kk);

                    if(outOfBound(nx, ny) || map[nx][ny] == islandNum) break;
                    if(map[nx][ny] == 0) {
                        bridgeLength++;
                        kk++;
                        continue;
                    }
                    int from = Math.min(map[nx][ny], islandNum);
                    int to = Math.max(map[nx][ny], islandNum);
                    if(bridgeLength > 1) bridgeList.add(new Bridge(from, to, bridgeLength));
                    break;
                }
            }
        }
    }

    public static void union(int a, int b) {
        int r1 = find(a);
        int r2 = find(b);
        if(r1 > r2) island[r1] = r2;
        else island[r2] = r1;
    }
    public static int find(int node) {
        if(node == island[node]) return node;
        return find(island[node]);
    }
    public static boolean outOfBound(int nx, int ny) {
        if(nx<0 || ny<0 || nx>=N || ny>=M) return true;
        return false;
    }

} 