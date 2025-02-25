import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] chi_ar, home_ar;
	static boolean[] visit;
	static int[][] dis_ar;
	static ArrayList<Integer> dis_list = new ArrayList<>();
	static int[] ar;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ArrayList<int[]> home_list = new ArrayList<>();
		ArrayList<int[]> chi_list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					home_list.add(new int[] {i,j});
				}
				if(temp == 2) {
					chi_list.add(new int[] {i,j});
				}
			}
		}
		chi_ar = new int[chi_list.size()][2];
		home_ar = new int[home_list.size()][2];
		dis_ar = new int[chi_list.size()][home_list.size()];
		visit = new boolean[chi_list.size()];
		ar = new int[M];

		chi_list.toArray(chi_ar);
		home_list.toArray(home_ar);

		setDis();
		selectM(0, 0);
		System.out.println(findMin());
	}
	
	public static void setDis() {
		for(int i=0; i<chi_ar.length; i++) {
			for(int j=0; j<home_ar.length; j++) {
				dis_ar[i][j] += Math.abs(chi_ar[i][0] - home_ar[j][0]) + Math.abs(chi_ar[i][1] - home_ar[j][1]);
			}
		}
	}

	public static void selectM(int cnt, int start) {
		if(cnt == M) {
			setMin(ar);
			return;
		}
		for(int i=start; i<chi_ar.length; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			ar[cnt] = i;
			selectM(cnt+1, start+1);
			visit[i] = false;
		}
	}

	public static void setMin(int[] ar) {
		int res = 0;
		for(int j=0; j<home_ar.length; j++) {
			int temp = Integer.MAX_VALUE;
			for(int i : ar) {
				temp = Math.min(dis_ar[i][j], temp);
			}
			res += temp;
		}
		dis_list.add(res);
	}

	public static int findMin() {
		int res = Integer.MAX_VALUE;
		for(int i : dis_list){
			res = Math.min(i, res);
		}
		return res;
	}
}
