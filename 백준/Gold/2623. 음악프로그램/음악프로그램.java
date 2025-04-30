import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] cost;
    static ArrayList<Integer> answer = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graph= new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        cost = new int[N+1];
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int[] ar = new int[cnt];
            for(int j=0; j<cnt; j++) {
                ar[j] = Integer.parseInt(st.nextToken());
            }
            for(int j=0; j<cnt; j++) {
                int num = ar[j];
                cost[num] += j;
                for(int k=0; k<j; k++) {
                    graph.get(num).add(ar[k]);
                }
            }
        }
        find();
        if(answer.size()==N) {
            StringBuilder sb = new StringBuilder();
            for(int i : answer) {
                sb.append(i).append("\n");
            }
            System.out.println(sb);
        } else System.out.println(0);
    }

    static boolean isEmpty() {
        for(int i=1; i<=N; i++) {
            if(cost[i] == 0) return false;
        }
        return true;
    }

    static void find() {
        while(!isEmpty()) {
            for(int i=1; i<=N; i++) {
                if(cost[i] != 0) continue;
                answer.add(i);
                cost[i] = -1;
                updateCost(i);
            }
        }
    }

    static void updateCost(int n) {
        for(int i=1; i<=N; i++) {
            List<Integer> li = graph.get(i);
            for(int j=li.size()-1; j>=0; j--) {
                if(li.get(j) == n) {
                    li.remove(j);
                    cost[i]--;
                }
            }
        }
    }

}
