import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static ArrayList<Fire> fires;
    static int[] dx={-1,-1,0,1,1,1,0,-1}, dy={0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws Exception {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fires = new ArrayList<>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Fire fire = new Fire(x, y, m, s, d, d%2, 1);
            fires.add(fire);
        }
        while(K-- > 0) {
            move();
            explotion();
        }
        System.out.println(getAnswer());
    }

    public static void move() {
        Fire[][] map = new Fire[N][N];
        ArrayList<Fire> list = new ArrayList<>();
        for(int i=fires.size()-1; i>=0; i--) {
            Fire fire = fires.get(i);
            int nx= (fire.x + N + dx[fire.d] * (fire.s%N)) % N; 
            int ny= (fire.y + N + dy[fire.d] * (fire.s%N)) % N;
            if(map[nx][ny] != null) {
                Fire ori_fire = map[nx][ny];
                ori_fire.cnt += 1;
                ori_fire.m += fire.m;
                ori_fire.s += fire.s;
                if(ori_fire.dState == 2) continue;
                if(ori_fire.dState == fire.dState) ori_fire.d = 0;
                else {
                    ori_fire.d = 1;
                    ori_fire.dState = 2; 
                }
            }
            else {
                fire.x = nx;
                fire.y = ny;
                map[nx][ny] = fire;
                list.add(fire);
            }
        }
        fires.clear();
        fires.addAll(list);
    }

    public static void explotion() {
        ArrayList<Fire> list = new ArrayList<>();
        for(int i=fires.size()-1; i>=0; i--) {
            Fire fire = fires.get(i);
            if(fire.cnt < 2) {
                list.add(fire);
                continue;
            }
            if(fire.m < 5) continue; 

            int tmpd = fire.d;
            for(int j=0; j<4; j++) {
                int x = fire.x;
                int y = fire.y;
                int m = fire.m / 5;
                int s = fire.s / fire.cnt;
                int d = tmpd;
                Fire nFire = new Fire(x, y, m, s, d, d%2, 1);
                list.add(nFire);
                tmpd+=2;
            }
        }
        fires.clear();
        fires.addAll(list);
    }

    public static int getAnswer() {
        int cnt = 0;
        for(Fire fire : fires) {
            cnt += fire.m;
        }
        return cnt;
    }

}

class Fire {
    public int x;
    public int y;
    public int m;
    public int s;
    public int d;
    public int dState; // 0:짝 1:홀 2:다른거합쳐짐
    public int cnt;
    public Fire(int x, int y, int m, int s, int d, int dState, int cnt) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.s = s;
        this.d = d;
        this.dState = dState;
        this.cnt = cnt;
    }
}