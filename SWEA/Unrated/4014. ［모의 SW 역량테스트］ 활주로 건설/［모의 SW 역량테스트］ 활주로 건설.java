import java.io.*;
import java.util.*;

public class Solution {

    static int N, X, answer;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N]; 
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<N; i++) {
                findRow(i, 0);
                findCol(0, i);
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static void findRow(int x, int y) {
        int now = map[x][y];
        int cnt = 1;
        boolean chk = false;
        while(y+1<N) {
            y += 1;
            if(chk) {
                if(map[x][y] == now) {
                    cnt++;
                    if(cnt == X) {
                        chk = false;
                        cnt = 0;
                    }
                    continue;
                } else {
                    return;
                }
            } else {
                if(map[x][y] == now) {
                    cnt++;
                    continue;
                } else if(map[x][y] == now+1) {
                    if(cnt < X) return;
                    cnt = 1;
                    now = map[x][y];
                    continue;
                } else if(map[x][y] == now-1) {
                    chk = true;
                    now = map[x][y];
                    cnt = 1;
                    continue;
                } else {
                    return;
                }
            }
        }
        if(chk && cnt < X) return;
        answer++;
    }

    public static void findCol(int x, int y) {
        int now = map[x][y];
        int cnt = 1;
        boolean chk = false;
        while(x+1<N) {
            x += 1;
            if(chk) {
                if(map[x][y] == now) {
                    cnt++;
                    if(cnt == X) {
                        chk = false;
                        cnt = 0;
                    }
                    continue;
                } else {
                    return;
                }
            } else {
                if(map[x][y] == now) {
                    cnt++;
                    continue;
                } else if(map[x][y] == now+1) {
                    if(cnt < X) return;
                    cnt = 1;
                    now = map[x][y];
                    continue;
                } else if(map[x][y] == now-1) {
                    chk = true;
                    now = map[x][y];
                    cnt = 1;
                    continue;
                } else {
                    return;
                }
            }
        }
        if(chk && cnt < X) return;
        answer++;
    }

}