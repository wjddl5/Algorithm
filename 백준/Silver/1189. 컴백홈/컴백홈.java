
import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C, K;
	static char[][] ar;
	static boolean[][] visit;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static ArrayList<Integer> count = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ar = new char[R][C];
		visit = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				ar[i][j] = str.charAt(j);
			}
		}
		dfs(R-1, 0, 1);
		print();
	}
	
	public static void dfs(int startX, int startY, int startStep) {
		if(startX==0 && startY==C-1) {
			count.add(startStep);
			return;
		}
		visit[startX][startY] = true;
		
		for(int k=0; k<4; k++) {
			int nx = startX + dx[k]; 
			int ny = startY + dy[k];
			
			if(nx<0 || nx>=R || ny<0 || ny>=C || visit[nx][ny] || ar[nx][ny] == 'T') continue;
			
			dfs(nx, ny, startStep + 1);
		}
		visit[startX][startY] = false;
	}
	
	public static void print() {
		int cnt = 0;
		for(int i : count) {
			if(i == K) cnt++;
		}
		System.out.println(cnt);
	}

}
