import java.io.*;
import java.util.*;

public class Main {

    static int[] paper = {0,5,5,5,5,5};
    static int[][] ar = new int[10][10];
    static int res = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        for(int i=0; i<10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        find(0, 0, 0);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    public static void find(int x, int y, int cnt) {
        if(cnt > res) return;
        if(x==9 && y==10) {
            res = Math.min(res, cnt);
            return;
        } 
        if(y > 9) {
            find(x+1,0,cnt);
            return;
        }
        if(ar[x][y] == 1) {
            for(int k=5; k>0; k--) { //size
                if(check(x, y, k)) {
                    on(x, y, k);
                    find(x, y+1, cnt+1);
                    off(x, y, k);
                }
            }
        }else find(x, y+1, cnt);
    }

    public static boolean check(int x, int y, int size) {
        if(paper[size] < 1) return false;
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if(i<0 || j<0 || i>9 || j>9 || ar[i][j] == 0) return false;
            }
        }
        return true;
    }

    public static void on(int x, int y, int size) {
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                ar[i][j] = 0;
            }
        }
        paper[size] --;
    }

    public static void off(int x, int y, int size) {
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                ar[i][j] = 1;
            }
        }
        paper[size] ++;
    }

}