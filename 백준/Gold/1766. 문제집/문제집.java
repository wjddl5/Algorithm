import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] ar;
    static int[] count;
    static StringBuilder sb =  new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;      

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ar = new ArrayList[N+1];
        count = new int[N+1];
        for(int i=1; i<=N; i++) {
            ar[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ar[a].add(b);
            count[b]++;
        }

        find();
        System.out.println(sb);                      
    }

    static void find() {
        for(int i=1; i<=N; i++) {
            if(count[i] == 0) {
                count[i] = -1;
                sb.append(i).append(" ");
                for(int n : ar[i]) {
                    count[n]--;
                }
                i = 0;
                continue;
            }
        }
    }

}