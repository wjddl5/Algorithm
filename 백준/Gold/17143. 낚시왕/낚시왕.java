import java.io.*;
import java.util.*;

public class Main {

    static int R, C, fishingCol=0, answer;
    static int[][] map;
    static Shark[] ar = new Shark[10001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()); // 1U 2D 3R 4L
            int size = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(x, y, speed, dir, size);
            map[x][y] = size;
            ar[size] = shark;
        }
        while(fishingCol < C) {
            fishing();
            sharkMove();
            fishingCol ++;
        }
        System.out.println(answer);
    }

    public static void fishing() {
        for(int i=0; i<R; i++) {
            int size = map[i][fishingCol];
            if(size > 0) {
                answer += size;
                map[i][fishingCol] = 0;
                ar[size] = null;
                return;
            }
        }
    }

    public static void sharkMove() {
        int[][] copy = new int[R][C];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for(Shark shark : ar) {
            if(shark == null) continue;
            int x = shark.x;
            int y = shark.y;
            int dir = shark.dir;
            int size = shark.size;
            int moveSize = 0;

            if(dir < 3) moveSize = shark.speed % ((R-1) * 2);
            else moveSize = shark.speed % ((C-1) * 2);

            for(int k=0; k<moveSize; k++) {
                int nx = x + dx[dir-1];
                int ny = y + dy[dir-1];

                if(nx<0 || ny<0 || nx>=R || ny>=C) {
                    dir = changeDir(dir);
                    x = nx + dx[dir-1] * 2;
                    y = ny + dy[dir-1] * 2;
                }else {
                    x = nx;
                    y = ny;    
                }
            }
            if(copy[x][y] < size) ar[copy[x][y]] = null;
            copy[x][y] = size;
            Shark newShark = new Shark(x, y, shark.speed, dir, size);
            ar[size] = newShark;
        }
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                map[i][j] = copy[i][j];
            }
        }
    }

    public static int changeDir(int dir) {
        int newDir = 0;
        switch (dir) {
            case 1:
                newDir = 2;
                break;
            case 2:
                newDir = 1;
                break;
            case 3:
                newDir = 4;
                break;
            case 4:
                newDir = 3;
                break;
        }
        return newDir;
    }

}

class Shark{
    public int x;
    public int y;
    public int speed;
    public int dir;
    public int size;

    Shark(int x, int y, int speed, int dir, int size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }
}