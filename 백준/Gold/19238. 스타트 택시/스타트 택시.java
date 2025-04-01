import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, oil, stx, sty, enx, eny, succecsCnt;
    static boolean isGuest;
    static int[][] map;
    static boolean[][] v;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    static ArrayList<Guest> guestList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        oil = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                if(st.nextToken().equals("1")) map[i][j] = 1;
            }
        }
        st = new StringTokenizer(br.readLine());
        stx = Integer.parseInt(st.nextToken()) -1;
        sty = Integer.parseInt(st.nextToken()) -1;
        int num = 2; // 손님 번호
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) -1;
            int sy = Integer.parseInt(st.nextToken()) -1;
            int ex = Integer.parseInt(st.nextToken()) -1;
            int ey = Integer.parseInt(st.nextToken()) -1;
            guestList.add(new Guest(sx, sy, ex, ey));
            map[sx][sy] = num++;
        }
        // <- 인풋

        while(succecsCnt<M) {
            bfs();
            if(oil < 0) {
                oil = -1;
                break;
            }
        }
        System.out.println(oil);    
    }

    public static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        v = new boolean[N][N];
        int useOil = 0; // 사용한 기름
        int gx=100, gy=100; // 손님 위치 (맵 범위 밖으로 지정)
        queue.offer(new int[] {stx, sty}); // 시작위치 큐 추가
        v[stx][sty] = true;

        while(!queue.isEmpty()) {
            int qSize = queue.size();
            oil--;
            if(oil<0) return;
            useOil++;
            for(int i=0; i<qSize; i++) { // 기름 한칸으로 갈 수 있는 범위 탐색
                int[] tmp = queue.poll();
                int x = tmp[0];
                int y = tmp[1];
                if(!isGuest && map[x][y]>1) { // 시작 지점에 손님
                    gx = x;
                    gy = y;
                    oil++;      
                    useOil--;
                    break;
                }
                for(int k=0; k<4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    
                    if(nx<0 || ny<0 || nx>=N || ny>=N || v[nx][ny] || map[nx][ny]==1) continue;
                    if(!isGuest && map[nx][ny] > 1) {
                        if(gx>nx || (gx==nx && gy>ny)) {
                            gx = nx;
                            gy = ny;
                        }
                        v[nx][ny] = true;
                        continue;            
                    }
                    if(isGuest && nx==enx && ny==eny) { // 목적지 도착
                        isGuest = false;
                        //oil -= useOil;
                        if(oil<0)  return;
                        oil += useOil * 2;
                        succecsCnt++;
                        stx = nx;
                        sty = ny;
                        return;
                    } 
                    queue.offer(new int[] {nx, ny});
                    v[nx][ny] = true;
                }
            }
            if(gx < 21) { // 찾은 손님 있음
                stx = gx;
                sty = gy;
                //oil -= useOil;
                Guest g = guestList.get(map[stx][sty] - 2);
                map[stx][sty] = 0;
                enx = g.ex; // 목적지 지정
                eny = g.ey;
                isGuest = true; // 손님 태운 상태
                return;
            }
        }
        if(!isGuest && succecsCnt<M && gx>20) { // 손님 태워야 하는데 못찾음
            oil = -1;
            return;
        }
    } 

}

class Guest{
    public int sx;
    public int sy;
    public int ex;
    public int ey;
    public Guest(int sx, int sy, int ex, int ey) {
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
    }    
}