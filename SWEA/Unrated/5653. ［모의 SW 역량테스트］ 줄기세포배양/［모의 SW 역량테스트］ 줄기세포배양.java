import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, K;
    static boolean[][] map;
    static ArrayList<Sepo> sepoList;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());  
            K = Integer.parseInt(st.nextToken());
            map = new boolean[N + K*2][M + K*2];
            sepoList = new ArrayList<>();

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    if(n == 0) continue;
                    map[i+K][j+K] = true; 
                    sepoList.add(new Sepo(i+K, j+K, n, n, 1));
                }
            }
            updateCell();
            while(K-- > 0) {
                bfs();
                updateCell();
            }
            sb.append("#"+tc+" "+sepoList.size()+"\n");
        }
        System.out.println(sb);
    }

    static void updateCell() {
        ArrayList<Sepo> tmpList = new ArrayList<>();
        for(Sepo sepo : sepoList) {        
            sepo.hp --;
            if(sepo.state == 2 && sepo.hp == 0) continue;
            if(sepo.state == 1 && sepo.hp == 0) {
                sepo.state = 2;
                sepo.hp = sepo.level + 1;
            }
            tmpList.add(sepo);
        }
        sepoList = tmpList;
        sepoList.sort((o1, o2) -> o2.level - o1.level);
    }

    static void bfs() {
        ArrayList<Sepo> tmpList = new ArrayList<>();
        for(Sepo sepo : sepoList) {
            if(sepo.state == 2 && sepo.level == sepo.hp) {
                for(int k=0; k<4; k++) {
                    int nx = sepo.x + dx[k];
                    int ny = sepo.y + dy[k];
                    if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length || map[nx][ny]) continue;
                    map[nx][ny] = true;
                    tmpList.add(new Sepo(nx, ny, sepo.level, sepo.hp, 1));
                }
            }
        }
        for(Sepo s : tmpList) {
            sepoList.add(s);
        }
    }

}

class Sepo {
    public int x;
    public int y;
    public int level;
    public int hp;
    public int state;
    Sepo(int x, int y, int level, int hp, int state) {
        this.x = x;
        this.y = y;
        this.level = level;
        this.hp = hp;
        this.state = state;
    }
}