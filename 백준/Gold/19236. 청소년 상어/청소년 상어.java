import java.io.*;
import java.util.*;

public class Main {
    
    static int maxSum = 0;
    static int[] dx={-1,-1,0,1,1,1,0,-1}, dy={0,-1,-1,-1,0,1,1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map = new int[4][4];
        Fish[] fishAr = new Fish[17];
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken()) -1;
                fishAr[n] = new Fish(i, j, k, true);;
                map[i][j] = n;
            }
        }
        int fishNum = map[0][0];
        fishAr[fishNum].life = false;
        Shark shark = new Shark(0, 0, fishAr[fishNum].k, fishNum);
        map[0][0] = -1;
        
        dfs(shark, map, fishAr);
        System.out.println(maxSum);
    }

    public static void move(int[][] map, Fish[] fishAr) {
        for(int i=1; i<17; i++) {
            Fish fish = fishAr[i];
            if(!fish.life) continue;

            for(int d=0; d<8; d++) {
                int nk = (fish.k + d) % 8;
                int nx = fish.x + dx[nk];
                int ny = fish.y + dy[nk];
                if(nx<0 || ny<0 || nx>3 || ny>3 || map[nx][ny]<0) continue;
                
                if(map[nx][ny] > 0) {
                    fishAr[map[nx][ny]].x = fish.x;
                    fishAr[map[nx][ny]].y = fish.y;
                }
                map[fish.x][fish.y] = map[nx][ny];
                map[nx][ny] = i;
                fish.x = nx;
                fish.y = ny;
                fish.k = nk;
                break;   
            }
        }
    }

    public static void dfs(Shark shark, int[][] map, Fish[] fishAr) {             
        maxSum = Math.max(maxSum, shark.eat);
        move(map, fishAr);
        
        for(int d=1; d<4; d++) {
            int nx = shark.x + dx[shark.k] * d;
            int ny = shark.y + dy[shark.k] * d;
            if(nx<0 || ny<0 || nx>3 || ny>3) return;
            if(map[nx][ny] < 1) continue;
            
            int[][] copyMap = copyMap(map);
            Fish[] copyFishAr  = copyFishAr(fishAr);
            
            int fishNum = copyMap[nx][ny];
            copyFishAr[fishNum].life = false;
            Shark newShark = new Shark(nx, ny, copyFishAr[fishNum].k, shark.eat+fishNum);
            copyMap[shark.x][shark.y] = 0;
            copyMap[nx][ny] = -1;
            dfs(newShark, copyMap, copyFishAr);
        }
    }

    public static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[4][4];
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    public static Fish[] copyFishAr(Fish[] fishAr) {
        Fish[] copyFishAr = new Fish[17];
        for(int i=1; i<17; i++) {
            copyFishAr[i] = new Fish(fishAr[i].x, fishAr[i].y, fishAr[i].k, fishAr[i].life);
        }
        return copyFishAr;
    }

}

class Shark {
    public int x;
    public int y;
    public int k;
    public int eat;
    public Shark(int x, int y, int k, int eat) {
        this.x = x;
        this.y = y;
        this.k = k;
        this.eat = eat;
    }
}

class Fish {
    public int x;
    public int y;
    public int k;
    public boolean life;
    public Fish(int x, int y, int k, boolean life) {
        this.x = x;
        this.y = y;
        this.k = k;
        this.life = life;
    }
}