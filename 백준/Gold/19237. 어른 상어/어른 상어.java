import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, answerTime;
    static Shark[] sharks;
    static Shark[][] map;
    static Smell[][] smellMap;
    static int[][][] sharkDir;
    static int[] dx={-1, 1, 0, 0}, dy={0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Shark[N][N];
        smellMap = new Smell[N][N];
        sharks = new Shark[M+1];
        sharkDir = new int[M+1][4][4];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) continue;
                Shark shark = new Shark(num, i, j, 0);
                map[i][j] = shark;
                sharks[num] = shark;
            }
        }
        // 상어 초기 방향
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }
        // 상어 우선 방향
        int num = 1;
        while(num <= M) {
            for(int i=0; i<4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<4; j++) {
                    sharkDir[num][i][j] = Integer.parseInt(st.nextToken()) -1;
                }
            }
            num++;
        }
        move();
        System.out.println(answerTime > 1000 ? -1 : answerTime);
    }

    public static void move() {
        int time=0, deadShark=0; 
        while(deadShark<M-1 && time<1001) {
            // 냄새 남김
            for(int i=M; i>0; i--) {
                if(sharks[i] == null) continue;
                Shark shark = sharks[i];
                smellMap[shark.x][shark.y] = new Smell(shark.num, K);
            }
            // 상어 이동
            for(int i=M; i>0; i--) {
                if(sharks[i] == null) continue;
                Shark shark = sharks[i];
                int check = check(shark.x, shark.y);
                int nk, nx, ny;
                switch (check) {
                    case 0:
                        nk = sharkDir[shark.num][shark.dir][0];
                        nx = shark.x + dx[nk];
                        ny = shark.y + dy[nk];
                        if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                        map[shark.x][shark.y] = null;
                        shark.x = nx;
                        shark.y = ny;
                        shark.dir = nk;
                        if(map[nx][ny] != null) {
                            deadShark++;
                            sharks[map[nx][ny].num] = null;
                        }
                        map[nx][ny] = shark;
                        break;
                    case 1:
                        for(int k=0; k<4; k++) {
                            nk = sharkDir[shark.num][shark.dir][k];
                            nx = shark.x + dx[nk];
                            ny = shark.y + dy[nk];
                            if(nx<0 || ny<0 || nx>=N || ny>=N || smellMap[nx][ny] != null) continue;
                            map[shark.x][shark.y] = null;
                            shark.x = nx;
                            shark.y = ny;
                            shark.dir = nk;
                            if(map[nx][ny] != null) {
                            deadShark++;
                            sharks[map[nx][ny].num] = null;
                            }
                            map[nx][ny] = shark;
                            break;
                        }
                        break;
                    case 2:
                        for(int k=0; k<4; k++) {
                            nk = sharkDir[shark.num][shark.dir][k];
                            nx = shark.x + dx[nk];
                            ny = shark.y + dy[nk];
                            if(nx<0 || ny<0 || nx>=N || ny>=N || smellMap[nx][ny].sharkNum != shark.num) continue;
                            map[shark.x][shark.y] = null;
                            shark.x = nx;
                            shark.y = ny;
                            shark.dir = nk;
                            if(map[nx][ny] != null) {
                            deadShark++;
                            sharks[map[nx][ny].num] = null;
                            }
                            map[nx][ny] = shark;
                            break;
                        }
                        break;
                }
            }
            // 냄새 줄어듬
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(smellMap[i][j] == null) continue;
                    if(--smellMap[i][j].time == 0) smellMap[i][j] = null;
                }
            }
            time++;
        }
        answerTime = time;
    }

    public static int check(int x, int y) {
        boolean isOther=false; 
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
            if(smellMap[nx][ny] == null) return 1;  // 4방 중 빈칸 있음
            if(smellMap[nx][ny].sharkNum == map[x][y].num) isOther=true;
        }
        if(isOther) return 2; // 4방 중 내 상어향 있음
        return 0; // 해당 없음
    }
}

class Shark {
    public int num;
    public int x;
    public int y;
    public int dir;
    public Shark(int num, int x, int y, int dir) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

class Smell {
    public int sharkNum;
    public int time;
    public Smell(int sharkNum, int time) {
        this.sharkNum = sharkNum;
        this.time = time;
    }
}