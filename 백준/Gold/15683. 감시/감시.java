import java.io.*;
import java.util.*;

public class Main {

    static int N, M, cctvCount, findCount;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static Map<Integer, int[][]> cctvMap;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int total = N * M;

        map = new int[N][M];

        ArrayList<Cctv> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if(n > 0) {
                    total--;
                    if(n < 6) list.add(new Cctv(i, j, n));
                }
            }
        }

        Cctv[] cctvAr = new Cctv[8];
        int idx = 0;
        for(Cctv cctv : list) {
            if(cctv.id == 5) {
                for(int k=0; k<4; k++) {
                    int x = cctv.x;
                    int y = cctv.y;
                    while(true) {
                        x += dx[k];
                        y += dy[k];
                        if(!isArray(x, y) || map[x][y] == 6) break;
                        if(map[x][y] == 0) {
                            map[x][y] = -1;
                            total--;
                        }
                    }
                }
            } else {
                cctvAr[idx] = cctv;
                idx++;
                cctvCount = idx;
            }
        }


        cctvMap = new HashMap<>();
        cctvMap.put(1, new int[][] { {0}, {1}, {2}, {3} });
        cctvMap.put(2, new int[][] { {0, 2}, {1, 3} });
        cctvMap.put(3, new int[][] { {0, 1}, {1, 2}, {2, 3}, {3, 0} });
        cctvMap.put(4, new int[][] { {0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1} });

        find(cctvAr, 0, 0);

        System.out.println(total - findCount);
    }

    static void find(Cctv[] cctvAr, int depth, int count) {
        if(depth == cctvCount) {
            findCount = Math.max(findCount, count);

            return;
        }

        Cctv cctv = cctvAr[depth];
        int[][] dd = cctvMap.get(cctv.id);

        for(int[] d : dd) {    
            int cnt = 0;
            ArrayList<int[]> list = new ArrayList<>();

            for(int k : d) {
                int x = cctv.x;
                int y = cctv.y;
                while(true) {
                    x += dx[k];
                    y += dy[k];
                    if(!isArray(x, y) || map[x][y] == 6) break;
                    if(map[x][y] == 0) {
                        map[x][y] = -1;
                        list.add(new int[] {x, y});
                        cnt++;
                    }
                }
            }

            find(cctvAr, depth + 1, count + cnt);

            for(int[] ar : list) {
                map[ar[0]][ar[1]] = 0;
            }
        }  

    }

    static boolean isArray(int x, int y) {
        return x >=0 && y >= 0 && x < N && y < M;
    }

}

class Cctv {
    int x, y, id;
    Cctv(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
}