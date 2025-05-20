import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count;
    static int[][] map;
    static Node[][] nodeMap;
    static int[] check, dx={-1,0,1,0}, dy={0,1,0,-1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        nodeMap = new Node[N][M];

        String str;
        for(int i=0; i<N; i++) {
            str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j) - 48;
            }
        }
        setNodeMap();
        find();
        System.out.println(sb);
    }

    static void setNodeMap() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit  = new boolean[N][M];
        int num = 1;
        int cnt = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] > 0 || nodeMap[i][j] != null) continue;

                ArrayList<int[]> list = new ArrayList<>();    
                queue.offer(new int[] {i, j});
                list.add(new int[] {i, j});
                visit[i][j] = true;
                cnt = 1;

                while(!queue.isEmpty()) {
                    int[] tmp = queue.poll();
                    int x = tmp[0];
                    int y = tmp[1];

                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny] || map[nx][ny] > 0) continue;

                        queue.offer(new int[] {nx, ny});
                        list.add(new int[] {nx, ny});
                        visit[nx][ny] = true;
                        cnt++;
                    }
                }
                Node node = new Node(num, cnt);

                for(int[] ar : list) {
                    nodeMap[ar[0]][ar[1]] = node;
                }

                num++;
            }
        }
        count = num;
    }

    static void find() {
        check = new int[1000001];
        int wall = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    sb.append(0);
                    continue;
                }
                int cnt = 1;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == 1) continue;

                    if(check[nodeMap[nx][ny].num] != wall){
                        cnt += nodeMap[nx][ny].cost;
                        check[nodeMap[nx][ny].num] = wall;
                    } 
                }
                sb.append(cnt%10);
                wall++;
            }
            sb.append("\n");
        }
    }

}

class Node {
    int num;
    int cost;
    Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}