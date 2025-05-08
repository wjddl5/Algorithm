import java.io.*;
import java.util.*;

public class Main {
    static int[][][][][][][][][][][] map;
    static int[] dm={1,0,0,0,0,0,0,0,0,0,0, -1,0,0,0,0,0,0,0,0,0,0},
                 dn={0,1,0,0,0,0,0,0,0,0,0, 0,-1,0,0,0,0,0,0,0,0,0},
                 dO={0,0,0,1,0,0,0,0,0,0,0, 0,0,-1,0,0,0,0,0,0,0,0},
                 dp={0,0,1,0,0,0,0,0,0,0,0, 0,0,0,-1,0,0,0,0,0,0,0},
                 dq={0,0,0,0,1,0,0,0,0,0,0, 0,0,0,0,-1,0,0,0,0,0,0},
                 dr={0,0,0,0,0,1,0,0,0,0,0, 0,0,0,0,0,-1,0,0,0,0,0},
                 ds={0,0,0,0,0,0,1,0,0,0,0, 0,0,0,0,0,0,-1,0,0,0,0},
                 dt={0,0,0,0,0,0,0,1,0,0,0, 0,0,0,0,0,0,0,-1,0,0,0},
                 du={0,0,0,0,0,0,0,0,1,0,0, 0,0,0,0,0,0,0,0,-1,0,0},
                 dv={0,0,0,0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,0,0,-1,0},
                 dw={0,0,0,0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,0,0,0,-1};
    static long totalTomato, goodTomato;
    static int m, n, o, p , q, r, s, t, u, v, w, day = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));                          
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        o = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new int[m][n][o][p][q][r][s][t][u][v][w];

        Queue<int[]> queue = new ArrayDeque<>();
        for(int xi=0; xi<w; xi++) {
            for(int x=0; x<v; x++) {
                for(int ix=0; ix<u; ix++) {
                    for(int viii=0; viii<t; viii++) {
                        for(int vii=0; vii<s; vii++) {
                            for(int vi=0; vi<r; vi++) {
                                for(int V=0; V<q; V++) {
                                    for(int iv=0; iv<p; iv++) {
                                        for(int iii=0; iii<o; iii++) {
                                            for(int ii=0; ii<n; ii++) {
                                                st = new StringTokenizer(br.readLine());
                                                for(int i=0; i<m; i++) {
                                                    int tomato = Integer.parseInt(st.nextToken());
                                                    if(tomato > -1) totalTomato++;        
                                                    if(tomato == 1) {
                                                        goodTomato++;
                                                        queue.offer(new int[] {i,ii,iii,iv,V,vi,vii,viii,ix,x,xi});
                                                    }
                                                    map[i][ii][iii][iv][V][vi][vii][viii][ix][x][xi] = tomato;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        br.close();

        bfs(queue);
        System.out.println(totalTomato==goodTomato ? day : -1);
    }

    static void bfs(Queue<int[]> queue) {
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] tmp = queue.poll();
                int tm = tmp[0];
                int tn = tmp[1];
                int to = tmp[2];
                int tp = tmp[3];
                int tq = tmp[4];
                int tr = tmp[5];
                int ts = tmp[6];
                int tt = tmp[7];
                int tu = tmp[8];
                int tv = tmp[9];
                int tw = tmp[10];

                for(int k=0; k<22; k++) {
                    int nm = tm + dm[k];
                    int nn = tn + dn[k];
                    int no = to + dO[k];
                    int np = tp + dp[k];
                    int nq = tq + dq[k];
                    int nr = tr + dr[k];
                    int ns = ts + ds[k];
                    int nt = tt + dt[k];
                    int nu = tu + du[k];
                    int nv = tv + dv[k];
                    int nw = tw + dw[k];

                    if(nm<0 || nn<0 || no<0 || np<0 || nq<0 || nr<0 || ns<0 || nt<0 || nu<0 || nv<0 || nw<0 ||
                        nm>=m || nn>=n || no>=o || np>=p || nq>=q || nr>=r || ns>=s || nt>=t || nu>=u || nv>=v || nw>=w ||
                        map[nm][nn][no][np][nq][nr][ns][nt][nu][nv][nw] != 0) continue;

                    queue.offer(new int[] {nm, nn, no, np, nq, nr, ns, nt, nu, nv, nw});
                    map[nm][nn][no][np][nq][nr][ns][nt][nu][nv][nw] = 1;
                    goodTomato++;
                    
                    if(totalTomato==goodTomato) break;
                }
            }
            day++;
        }

    }

}