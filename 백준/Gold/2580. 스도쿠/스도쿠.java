import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] row, col, box;
    static int[][] map;
    static ArrayList<int[]> point = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[9][9];
        row = new boolean[9][10];
        col = new boolean[9][10];
        box = new boolean[9][10];
        
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if(n == 0) point.add(new int[] {i, j});
                else {
                    row[i][n] = true;
                    col[j][n] = true;
                    box[(i/3)*3 + (j/3)][n] = true;
                    // 0 1 2
                    // 3 4 5
                    // 6 7 8 
                    // 몇분면에 속하는지 체크
                }
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
            if(check(x, y, i)) {
                map[x][y] = i;
                row[x][i] = true;
                col[y][i] = true;
                box[(x/3)*3 + (y/3)][i] = true;

                dfs(depth - 1);

                map[x][y] = 0;
                row[x][i] = false;
                col[y][i] = false;
                box[(x/3)*3 + (y/3)][i] = false;
            }
        }   
    }

    static boolean check(int x, int y, int num) {
        if(row[x][num] || col[y][num] || box[(x/3)*3 + (y/3)][num]) return false;
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