import java.util.*;

class Solution {
    
    ArrayList<Integer>[] graph;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }
        
        for(int[] wire : wires) {
            int cnt = bfs(wire[0], wire[1], n);
            int ans = Math.abs((n - cnt) - cnt);
            answer = Math.min(answer, ans);
        }       
        return answer;
    }
    
    public int bfs(int start, int skip, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[n+1];
        
        q.offer(start);
        v[start] = true;
        
        int cnt = 1;
        while(!q.isEmpty()) {
            int node = q.poll();
            for(int newNode : graph[node]) {
                if(v[newNode] || newNode == skip) continue;
                q.offer(newNode);
                v[newNode] = true;
                cnt++;
            }
        }
        return cnt;
    }
}