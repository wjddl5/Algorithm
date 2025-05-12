import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[9][9];
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    static ArrayList<int[]> zeroList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        String str;
        for(int i=0; i<9; i++) {
            str = br.readLine();
            for(int j=0; j<9; j++) {
                int n = (int)str.charAt(j) - 48;
                map[i][j] = n;
                if(n == 0) zeroList.add(new int[] {i, j});
            }
        }

        find(0);
    }
    
    static void find(int cnt) {
        for(int i=cnt; i<zeroList.size();) {
            int[] zero = zeroList.get(i);
            int x = zero[0], y = zero[1];
            
            for(int n=1; n<10; n++) {
                map[x][y] = n;
                if(garoSero(x, y) && nemo(x, y)) find(cnt + 1);
            }
            map[x][y] = 0;
            return;
        }
        print();  
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }

    static boolean garoSero(int x, int y) {
        boolean[] ar = new boolean[10];
        for(int i=0; i<9; i++) {
            int n = map[i][y];
            if(n == 0) continue;

            if(ar[n]) return false;
            ar[n] = true;
        }

        Arrays.fill(ar, false);
        for(int j=0; j<9; j++) {
            int n = map[x][j];
            if(n == 0) continue;

            if(ar[n]) return false;
            ar[n] = true;
        }

        return true;
    }

    static boolean nemo(int x, int y) {
        boolean[] ar = new boolean[10];

        int[] point = getPoint(x, y);
        int a = point[0], b = point[1];
        
        for(int i=a; i<a+3; i++) {
            for(int j=b; j<b+3; j++) {
                int n = map[i][j];
                if(n == 0) continue;
                
                if(ar[n]) return false;
                ar[n] = true;
            }
        }
        return true;
    }

    static int[] getPoint(int x, int y) {
        if(x < 3) {
            if(y < 3) return new int[] {0, 0};
            if(y >= 3 && y < 6) return new int[] {0, 3};
            return new int[] {0, 6};
        }
        else if(x >= 3 && x < 6) {
            if(y < 3) return new int[] {3, 0};
            if(y >= 3 && y < 6) return new int[] {3, 3};
            return new int[] {3, 6};
        } 
        else {
            if(y < 3) return new int[] {6, 0};
            if(y >= 3 && y < 6) return new int[] {6, 3};
        }
        return new int[] {6, 6};
    }

}