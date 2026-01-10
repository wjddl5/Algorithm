import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        Arrays.fill(answer, "");

        for(int i=0; i<n; i++) {
            String str = Integer.toBinaryString(arr1[i] | arr2[i]);
            int length = str.length();
            
            if(length < n) {
                for(int k=length; k<n; k++) {
                    answer[i] += " ";
                }
            }
            
            for(int j=0; j<length; j++) {
                char c = str.charAt(j);
                
                if(c == '0') answer[i] += " ";
                else answer[i] += "#";
            }
        }
        return answer;
    }
}