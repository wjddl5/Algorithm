import java.io.*;
import java.util.*;

class Main {

    static class Shark {
        int size, speed, iq;
        Shark(int size, int speed, int iq) {
            this.size = size;
            this.speed = speed;
            this.iq = iq;
        }
    }

    static int N, eat;
    static ArrayList<Integer>[] graph;
    static int[] match; // [먹힌 상어] = 먹은 상어
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        match = new int[N];
        visit = new boolean[N];

        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
            match[i] = -1;
        } 
        
        Shark[] sharks = new Shark[N];   
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            Shark shark = new Shark(
                Integer.parseInt(st.nextToken()), // size
                Integer.parseInt(st.nextToken()), // speed
                Integer.parseInt(st.nextToken()) // iq
            );
            sharks[i] = shark;     
        }

        // 먹을 수 있는 상어들 그래프
        // s1(i)이 s2(j)를 먹을 수 있음.
        for(int i=0; i<N; i++) {
            Shark s1 = sharks[i];
            for(int j=0; j<N; j++) {
                if(i == j) continue; 
                Shark s2 = sharks[j];

                if (s1.size >= s2.size && s1.speed >= s2.speed && s1.iq >= s2.iq) {
                    if (s1.size == s2.size && s1.speed == s2.speed && s1.iq == s2.iq) {
                        if (i > j) continue; // 능력치가 완전히 동일하다면 인덱스로 우선순위 설정
                    }
                    graph[i].add(j);
                }        
            }
        }


        for(int i=0; i<N; i++) { // 상어 한 마리가
            for(int j=0; j<2; j++) { // 최대 2마리 먹기 가능
                Arrays.fill(visit, false);
                if(dfs(i)) eat++;
            }
        }
        System.out.println(N - eat);
    }

    static boolean dfs(int node) {
        if(visit[node]) return false;
        visit[node] = true;

        for(int n : graph[node]) {
            if(match[n] == -1 || dfs(match[n])) { // 기존 매칭 밀어낼 수 있으면
                match[n] = node;
                return true;
            }   
        }

        return false;
    }
}