import java.io.*;
import java.util.*;

public class Main {

	static int N, count=0;
	static ArrayList<int[]> list  = new ArrayList<>();

	static boolean[] visit1 = new boolean[15];
	static boolean[] visit2 = new boolean[30];
	static boolean[] visit3 = new boolean[30];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		br.close();

		find(0);
		System.out.println(count);
	}

	public static void find(int row) {
		if(row == N) {
			count++;
			return;
		}

		for(int col=0; col<N; col++) {
			boolean chk = queenCheck(row, col);
			if(chk) {
				visit1[col] = true;
				visit2[row+col] = true;
				visit3[row-col + 13] = true;
				find(row+1);
				visit1[col] = false;
				visit2[row+col] = false;
				visit3[row-col + 13] = false;
			}		
		}
	}

	public static boolean queenCheck(int row, int col) {
		if(visit1[col] || visit2[row+col] || visit3[row-col+13]) {
			return false;
		}	
		return true;
	}
}