import java.io.*;
import java.util.*;

public class Main {

    static int R, C, T, row1, row2;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n==0) continue;
                if(n==-1) row2 = i;
                map[i][j] = n;
            }
        }
        row1 = row2-1;

        int time = 0;
        while(time<T) {
            mise();
            clear1();
            clear2();
            time++;
        }
        System.out.println(count());
    }

    public static void mise() {
        ArrayList<int[]> list = new ArrayList<>();
        int[][] copy = new int[R][C];
        copy[row1][0] = -1;
        copy[row2][0] = -1;

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j]<1) continue;
                list.add(new int[] {i, j});
            }
        }

        for(int[] ar : list) {
            int i = ar[0];
            int j = ar[1];

            ArrayList<Integer> kList = new ArrayList<>();      
            for(int k=0; k<4; k++) {
                int nx = i + dx[k];
                int ny = j + dy[k];
                
                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny]==-1) continue;
                kList.add(k);
            }

            int newMise = map[i][j] / 5;
            copy[i][j] += map[i][j] - newMise * kList.size();
            
            for(int k : kList) {
                copy[i + dx[k]][j + dy[k]] += newMise;
            }
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                map[i][j] = copy[i][j];
            }
        }
    }

    public static void clear1() {
        int i = row1;
        int j = 0;
        
        while(i > 0) {
            i--;
            if(map[i+1][j] == -1) {map[i][j] = 0; continue;}
            map[i+1][j] = map[i][j];
        }
        while(j < C-1) {
            j++;
            map[i][j-1] = map[i][j];
        }
        while(i < row1) {
            i++;
            map[i-1][j] = map[i][j];
        }
        while(j > 0) {
            j--;
            if(map[i][j] == -1) {map[i][j+1] = 0; continue;}
            map[i][j+1] = map[i][j];
        }
    }

    public static void clear2() {
        int i = row2;
        int j = 0;
        while(i < R-1) {
            i++;
            if(map[i-1][j] == -1) {map[i][j] = 0; continue;}
            map[i-1][j] = map[i][j];
        }
        while(j < C-1) {
            j++;
            map[i][j-1] = map[i][j];
        }
        while(i > row2) {
            i--;
            map[i+1][j] = map[i][j];
        }
        while(j > 0) {
            j--;
            if(map[i][j] == -1) {map[i][j+1] = 0; continue;}
            map[i][j+1] = map[i][j];
        }
    }

    public static int count() {
        int cnt = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j]<1) continue;
                cnt += map[i][j];
            }
        }
        return cnt;
    }
}