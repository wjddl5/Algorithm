import java.io.*;
import java.util.*;

public class Main {

    static int[][] cd = {{0,1},{-1,0},{0,-1},{1,0}};
    static boolean[][] ar = new boolean[101][101];
    static int N, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dragonCurve(x, y, d, g);
        }
        find();
        System.out.println(answer);
    }

    public static void dragonCurve(int x, int y, int d, int g) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d);

        for(int s=1; s<=g; s++) {
            for(int i=list.size()-1; i>=0; i--) {
                int n = list.get(i);
                if(n==3) n=0;
                else n++;
                list.add(n);
            }
        }
        ar[x][y] = true;
        for(int n : list) {
            x += cd[n][0];
            y += cd[n][1];
            ar[x][y]  = true;
        }
    }
    
    public static void find() {
        int cnt = 0;
        for(int i=0; i<100;  i++)  {
            for(int j=0; j<100; j++) {
                if(ar[i][j] && ar[i+1][j] && ar[i][j+1] && ar[i+1][j+1]) cnt++; 
            }
        }
        answer = cnt;
    }


}

