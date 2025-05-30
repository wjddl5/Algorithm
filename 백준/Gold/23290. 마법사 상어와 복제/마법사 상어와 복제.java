import java.io.*;
import java.util.*;

public class Main {
    static int sharkX, sharkY, count;
    static Queue<Fish>[][] map;
    static int[][] smell_map;
    static int[] dx={0,-1,-1,-1,0,1,1,1}, dy={-1,-1,0,1,1,1,0,-1};
    static int[] sx={-1,0,1,0}, sy={0,-1,0,1};
    static ArrayList<int[]> eatFishList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        map = new ArrayDeque[4][4];
        smell_map = new int[4][4];
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                map[i][j] = new ArrayDeque<>();
            }
        }
        
        for(int i=0; i<M; i++) {
            st =  new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            int d = Integer.parseInt(st.nextToken()) -1;
            map[x][y].offer(new Fish(x, y, d));
        }

        st = new StringTokenizer(br.readLine());
        sharkX = Integer.parseInt(st.nextToken()) -1;
        sharkY = Integer.parseInt(st.nextToken()) -1;
        find(S);
        System.out.println(getFishCount());
    }

    static void find(int S) {
        while(S-- > 0) {
            ArrayList<Fish> list = getFish();
            moveFish(list);

            count = -1;
            eatFishList = new ArrayList<>();
            boolean[][] visit = new boolean[4][4];
            findMoveShark(sharkX, sharkY, 0, 0, visit);

            eatFish();

            checkSmell();

            copyMagic(list);
        }
    }

    static ArrayList<Fish> getFish() {
        ArrayList<Fish> list = new ArrayList<>();
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                while(!map[i][j].isEmpty()){
                    list.add(map[i][j].poll());
                }
            }
        }
        return list;
    }

    static void moveFish(ArrayList<Fish> list) {
        ArrayList<Fish> moveList = new ArrayList<>();

        fishList:
        for(Fish fish : list) {
            for(int k=0; k<8; k++) {
                int nd = fish.d - k;
                if(nd  < 0) nd += 8;
                int nx = fish.x + dx[nd];
                int ny = fish.y + dy[nd];
                if(nx<0 || ny<0 || nx>3 || ny>3 ||
                smell_map[nx][ny] > 0 || (nx==sharkX && ny==sharkY)) continue;
                
                moveList.add(new Fish(nx, ny, nd));
                continue fishList;
            }
            moveList.add(fish);
        }
        
        for(Fish fish : moveList) {
            map[fish.x][fish.y].offer(fish);
        }
    }

    
    static void findMoveShark(int x, int y, int cnt, int depth, boolean[][] visit) {
        if(depth == 3) {
            if(count < cnt) {
                count = cnt;
                sharkX = x;
                sharkY = y;
                eatFishList.clear();
                for(int i=0; i<4; i++) {
                    for(int j=0; j<4; j++) {
                        if(visit[i][j]) eatFishList.add(new int[] {i ,j});
                    }
                }
            }
            return;
        }
        for(int k=0; k<4; k++) {
            int nx = x + sx[k];
            int ny = y + sy[k];
            int ncnt = cnt;
            if(nx<0 || ny<0 || nx>3 || ny>3) continue;

            boolean chk = false;
            if(!visit[nx][ny]) {
                ncnt += map[nx][ny].size();
                visit[nx][ny] = true;
                chk = true;
            }
            findMoveShark(nx, ny, ncnt, depth+1, visit);
            if(chk) visit[nx][ny] = false;
        }
    }

    static void eatFish() {
        for(int[] ar : eatFishList) {
            if(!map[ar[0]][ar[1]].isEmpty()) smell_map[ar[0]][ar[1]] = 3;
            map[ar[0]][ar[1]].clear();
        }
    }

    static void checkSmell() {
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(smell_map[i][j] > 0) smell_map[i][j]--;
            }
        }
    }

    static void copyMagic(ArrayList<Fish> list) {
        for(Fish fish : list) {
            map[fish.x][fish.y].offer(fish);
        }
    }

    static int getFishCount() {
        int cnt = 0;
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                cnt += map[i][j].size();
            }
        }
        return cnt;
    }

}

class Fish {
    int x;
    int y;
    int d;
    public Fish(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
