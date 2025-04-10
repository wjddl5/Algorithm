import java.io.*;
import java.util.*;

public class Solution {

    static int N, W, H, minCount;
    static int[][] map, orimap;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            minCount = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            orimap = new int[H][W];
            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    orimap[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[] ar = new int[N];
            perm(0, ar);
            sb.append("#").append(tc).append(" ").append(minCount).append("\n");
        }
        System.out.println(sb);
    }

    public static void perm(int depth, int[] ar) {
        if(depth == N) {
            copyMap();
            dropBead(ar);
            getCount();
            return;
        }
        for(int i=0; i<W; i++) {
            ar[depth] = i;
            perm(depth+1, ar);
        }
    }

    public static void copyMap() {
        map = new int[H][W];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                map[i][j] = orimap[i][j];
            }
        }
    }

    public static void dropBead(int[] ar) {
        for(int j : ar) {
            if(map[H-1][j] == 0) return;

            int i = 0;
            while(i<H) {
                if(map[i][j] == 0) {
                    i++;
                    continue;
                }
                brokeBlock(i, j);
                break;
            }
        } 
    }

    public static void brokeBlock(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {i, j, map[i][j]});
        map[i][j] = 0;
        while(!queue.isEmpty()) {
            int[] tar = queue.poll();
            int x = tar[0];
            int y = tar[1];
            int power = tar[2];
            for(int p=0; p<power; p++) {
                for(int k=0; k<4; k++) {
                    int nx = x + dx[k] * p;
                    int ny = y + dy[k] * p;
                    if(nx<0 || ny<0 || nx>=H || ny>=W || map[nx][ny]==0) continue;

                    if(map[nx][ny] == 1) {
                        map[nx][ny] = 0;
                        continue;
                    }
                    queue.offer(new int[] {nx, ny, map[nx][ny]});
                    map[nx][ny] = 0;
                }
            }
        }
        downBlock();
    }

    public static void downBlock() {
        for(int i=H-2; i>=0; i--) {
            for(int j=0; j<W; j++) {
                if(map[i][j] > 0 && map[i+1][j] == 0) {
                    int ni = i+1;
                    while(ni <= H) {
                        if(ni>=H || map[ni][j]>0) {
                            map[ni-1][j] = map[i][j];
                            map[i][j] = 0;
                            break;
                        }
                        else ni += 1;
                    }
                }
            }
        }
    }

    public static void getCount() {
        int cnt = 0;
        for(int i=H-1; i>=0; i--) {
            boolean isEmpty = true;
            for(int j=0; j<W; j++) {
                if(map[i][j] == 0) continue;
                cnt++;
                isEmpty = false;
            }
            if(isEmpty) break;
        }
        minCount = Math.min(minCount, cnt);
    }

}