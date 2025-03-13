import java.io.*;
import java.util.*;

public class Main {

    static int n, m, start, count;
    static int[] group;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        group = new int[n+1];
        for(int i=0; i<=n; i++) {
            group[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        if(cnt > 0) {
            start = Integer.parseInt(st.nextToken());
            for(int i=1; i<cnt; i++) {
                union(start, Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0; i<m; i++) {
            list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int cnt2 = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            list.get(i).add(n1);
            if(cnt2 > 1) {
                for(int j=1; j<cnt2; j++) {
                    int n2 = Integer.parseInt(st.nextToken());
                    list.get(i).add(n2);
                    union(n1, n2);
                }
            }
        }

        for(List<Integer> list2 : list) {
            boolean chk = true;
            for(int node : list2) {
                if(find(start) == find(node)) {
                    chk = false;
                    break;
                }
            }
            if(chk) count++;
        }
        
        System.out.println(count);
    }

    public static void union(int root, int node) {
        int n1 = find(root);
        int n2 = find(node);
        if(n1 != n2) group[n2] = n1; 
    }

    public static int find(int node) {
        if(group[node] == node) return node;
        else return group[node] = find(group[node]);
    }

}