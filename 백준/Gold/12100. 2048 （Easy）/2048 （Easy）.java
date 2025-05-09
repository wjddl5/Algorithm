import java.io.*;
import java.util.*;

public class Main {
    static int N, max;
    static int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                max = Math.max(max, n); 
            }
        }
        game(0, map);
        System.out.println(max);
    }

    static void game(int cnt, int[][] map) {
        if(cnt == 5) return;
        for(int k=0; k<4; k++) {
            game(cnt+1, setDir(map, k));
        }
    }

    static int[][] setDir(int[][] oriMap, int k) {
        int[][] map = copyMap(oriMap);
        int[] ar = new int[N];
        switch (k) {
            case 0:
                for(int j=0; j<N; j++) {
                    for(int i=0; i<N; i++) {
                        ar[i] = map[i][j];
                    }
                    int[] arr = move(ar);
                    for(int i=0; i<N; i++) {
                        map[i][j] = arr[i];
                    }
                }   
                break;
            case 1:
                for(int i=0; i<N; i++) {
                    for(int j=N-1; j>=0; j--) {
                        ar[N-1-j] = map[i][j];
                    }
                    int[] arr = move(ar);
                    for(int j=N-1; j>=0; j--) {
                        map[i][j] = arr[N-1-j];
                    }
                }    
                break;
            case 2:
                for(int j=0; j<N; j++) {
                    for(int i=N-1; i>=0; i--) {
                        ar[N-1-i] = map[i][j];
                    }
                    int[] arr = move(ar);
                    for(int i=N-1; i>=0; i--) {
                        map[i][j] = arr[N-1-i];
                    }
                }     
                break;
            case 3:
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        ar[j] = map[i][j];
                    }
                    int[] arr = move(ar);
                    for(int j=0; j<N; j++) {
                        map[i][j] = arr[j];
                    }
                }     
                break;
        }
        return map;
    }

    static int[] move(int[] ar) {
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            int now = ar[i];
            if(now == 0) continue;

            for(int j=i+1; j<N; j++) {
                if(ar[j] == 0) continue;
                if(ar[j] == now) {
                    ar[i] = now * 2;
                    ar[j] = 0;
                    break;
                }
                break;
            }
        }
        int i = 0;
        for(int n : ar) {
            if(n > 0) arr[i++]  = n;
            max = Math.max(max, n);
        }
        return arr;
    }

    static int[][] copyMap(int[][] map) {
        int[][] copy = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
               copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

}