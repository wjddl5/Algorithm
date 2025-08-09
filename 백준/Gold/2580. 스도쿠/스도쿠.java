import java.io.*;
import java.util.*;

public class Main {

    static int[][] map = new int[9][9];
    static ArrayList<int[]> point = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if(n == 0) point.add(new int[] {i, j});
            }
        }

        dfs(point.size());
    }

    static void dfs(int depth) {
        if(depth == 0) print();

        int[] ar = point.get(depth-1);
        int x = ar[0];
        int y = ar[1];
        for(int i=1; i<10; i++) {
            if(checkGaroSero(x, y, i) && checkRange(x, y ,i)) {
                map[x][y] = i;
                dfs(depth - 1);
                map[x][y] = 0;
            }
        }   
    }

    static boolean checkGaroSero(int x, int y, int num) {
        for(int i=0; i<9; i++) {
            if(map[x][i] == num || map[i][y] == num) return false;
        }
        return true;
    }

    static boolean checkRange(int x, int y, int num) {
        x = (x / 3) * 3; // 0,1,2 => 0 , 3,4,5 => 3 , 6,7,8 => 6
        y = (y / 3) * 3;
        
        for(int i=x; i<x+3; i++) {
            for(int j=y; j<y+3; j++) {
                if(map[i][j] == num) return false;
            }
        }
        return true;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }

}