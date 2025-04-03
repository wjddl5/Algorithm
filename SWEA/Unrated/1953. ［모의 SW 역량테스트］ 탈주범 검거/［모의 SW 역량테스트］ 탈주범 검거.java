import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int N, M, R, C, L, answer;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};
    static Queue<int[]> queue;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            answer = 0;
            map = new int[N][M];
            visit = new boolean[N][M];
 
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if(L > 0) bfs();
             
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
 
    public static void bfs() {
        queue= new ArrayDeque<>();
        int time = 1;
        queue.offer(new int[] {R, C});
        visit[R][C] = true;
        answer++;
        while(!queue.isEmpty() && time<L) {
            int qSize = queue.size();
            time++;
            for(int i=0; i<qSize; i++) {
                int[] xy = queue.poll();
                int x = xy[0];
                int y = xy[1];
                int type = map[x][y];
                find(x, y, type);
            }
        }
    }
 
    public static void find(int x, int y, int type) {     
        switch (type) {
            case 1:
                for(int k=0; k<4; k++) {
                    add(x, y, k);
                }
                break;
            case 2:
                for(int k=0; k<4; k+=2) {
                    add(x, y, k);
                }
                break;
            case 3:
                for(int k=1; k<4; k+=2) {
                    add(x, y, k);
                }
                break;
            case 4:
                for(int k=0; k<2; k++) {
                    add(x, y, k);
                }
                break;
            case 5:
                for(int k=1; k<3; k++) {
                    add(x, y, k);
                }
                break;
            case 6:
                for(int k=2; k<4; k++) {
                    add(x, y, k);
                }
                break;
            case 7:
                for(int k=0; k<4; k+=3) {
                    add(x, y, k);
                }
                break;
        }
    }
 
    public static void add(int x, int y, int k) {    
        int nx = x + dx[k];
        int ny = y + dy[k];
        if(boundCheck(nx, ny) && connectCheck(nx, ny, k)) {
            queue.offer(new int[] {nx, ny});
            visit[nx][ny] = true;
            answer++;
        }
    }
 
    public static boolean boundCheck(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M || visit[x][y] || map[x][y]==0) return false;
        return true;
    }
 
    public static boolean connectCheck(int x, int y, int k) { // 0:상  1:우  2:하  3:좌
        int type = map[x][y]; // 새로 나아갈 곳의 파이프 타입
        switch (k) {
            case 0:
                if(type==3 || type==4 || type==7) return false;
                break;
                case 1:
                if(type==2 || type==4 || type==5) return false;
                break;
                case 2:
                if(type==3 || type==5 || type==6) return false;
                break;
                case 3:
                if(type==2 || type==6 || type==7) return false;
                break;
        }
        return true;
    }
 
}