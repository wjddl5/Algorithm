import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] ar;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=-1; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        ar = new int[n+1];

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int low = Integer.parseInt(st.nextToken());
            int high = Integer.parseInt(st.nextToken());
            graph.get(low).add(high);
            ar[high] += 1;
        }

        find();
        System.out.println(sb);
    }

    public static void find() {
        int cnt = 0;
        while(cnt < n){
            for(int i=1; i<=n; i++) {
                if(ar[i] != 0) continue;
                
                sb.append(i).append(" ");
                ar[i] = -1;

                for(int node : graph.get(i)) {
                    ar[node] -= 1;
                }
                cnt++;
            }
        }
    }
    
}