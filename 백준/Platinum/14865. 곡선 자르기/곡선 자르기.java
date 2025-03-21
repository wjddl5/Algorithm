import java.io.*;
import java.util.*;

public class Main {

    static int[][] tmp_aar, aar;
    static Integer[] ar;
    static int n, res1, res2, minIndex;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        tmp_aar = new int[n][2];     
        int min = Integer.MAX_VALUE;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tmp_aar[i][0] = a; 
            tmp_aar[i][1] = b;
            int tmpMin = a + b;
            if(min > tmpMin) {
                min = tmpMin;
                minIndex = i;
            }
        }
        setMin();
        find();
        check();
        System.out.println(res1+" "+res2);
    }

    public static void setMin() {
        aar = new int[n][2];
        int zero = 0;
        int mid = n-minIndex;
        for(int i=minIndex; i<aar.length; i++) {
            aar[zero][0] = tmp_aar[i][0]; 
            aar[zero][1] = tmp_aar[i][1];
            zero ++; 
        }
        for(int i=0; i<minIndex; i++) {
            aar[mid][0] = tmp_aar[i][0];
            aar[mid][1] = tmp_aar[i][1];
            mid++;
        }
    }

    public static void find() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<aar.length-1; i++) {
            if(aar[i][0] == aar[i+1][0]) {
                if(aar[i][1] < 0 && aar[i+1][1] > 0) {
                    list.add(aar[i][0]);
                }
                if(aar[i][1] > 0 && aar[i+1][1] < 0) {
                    list.add(aar[i][0]);
                }
            }
        }
        ar = new Integer[list.size()];
        list.toArray(ar);

        compare();
    }

    public static void compare() {
        for(int i=0; i<ar.length; i+=2) {
            int tmp1 = ar[i];
            int tmp2 = ar[i+1];
            
            if(tmp1 > tmp2) {
                ar[i] = tmp2;
                ar[i+1] = tmp1;
            }
        }
    }

    public static void check() {
        for(int i=0; i<ar.length; i+=2) {
            count(ar[i], ar[i+1]);
        }
    }

    public static void count(int x1, int x2) {
        boolean chk1 = true;
        boolean chk2 = true;
        for(int i=0; i<ar.length; i+=2) {
            if(x1 == ar[i] && x2 == ar[i+1]) continue;

            if(x1 > ar[i] && x2 < ar[i+1]) chk1 = false;

            if(x1 < ar[i] && x2 > ar[i+1]) chk2 = false;
        }
        if(chk1) res1 ++;
        if(chk2) res2 ++;
    }

}