import java.io.*;
import java.util.*;

public class Main {
    static int N, count;
    static Node[] ar;
    static boolean[] visit;
    static int[] team;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb =  new StringBuilder();

        int T =  Integer.parseInt(br.readLine());
        while(T-- > 0) {
            count = 0;
            N = Integer.parseInt(br.readLine());
            ar = new Node[N+1];
            visit = new boolean[N+1];
            team = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                ar[i] = new Node(i, Integer.parseInt(st.nextToken()));
            }

            find();
            sb.append(N-count).append("\n");
        }
        System.out.println(sb);                      
    }

    static void find() {
        for(int i=1; i<=N; i++) {
            if(visit[i]) continue;
            dfs(ar[i]);
        }
    }
    
    static void dfs(Node node) {
        if(team[node.num] == 0) {
            team[node.num] = 1;
            dfs(ar[node.next]);
        }
        else if(team[node.num] == 1 && !visit[node.num]) {
            for(Node tmpNode=node; team[tmpNode.num]!=2; tmpNode=ar[tmpNode.next]) {     
                team[tmpNode.num] = 2;
                count++;
            }
        }    
        visit[node.num] = true;
    }

}

class Node {
    int num;
    int next;
    Node(int num, int next) {
        this.num = num;
        this.next = next;
    }
}