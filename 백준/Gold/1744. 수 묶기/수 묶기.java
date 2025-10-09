import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int maxSum = 0;
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();

        for(int i=1; i<=N; i++) {
            int n = Integer.parseInt(br.readLine());
            if(n > 1) plus.add(n);
            else if(n == 1) maxSum += n;
            else minus.add(n);
        }
        // input

        plus.sort((o1, o2) -> o2 - o1);
        minus.sort(null);

        for(int i=0; i<plus.size(); i+=2) {
            if(i + 1< plus.size()) maxSum += plus.get(i) * plus.get(i+1);
            else maxSum += plus.get(i);
        }

        for(int i=0; i<minus.size(); i+=2) {
            if(i + 1< minus.size()) maxSum += minus.get(i) * minus.get(i+1);
            else maxSum += minus.get(i);
        }
        
        
        System.out.println(maxSum);
    }
}