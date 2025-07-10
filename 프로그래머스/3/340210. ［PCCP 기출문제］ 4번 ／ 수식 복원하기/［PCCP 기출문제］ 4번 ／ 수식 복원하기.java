
import java.util.*;

class Solution {
    
    int max;
    int[] pns = new int[10]; // 1:X 2:O
    ArrayList<String> answer = new ArrayList<>();
    
    public String[] solution(String[] expressions) {
        for(String input : expressions) {
            String[] str = input.split(" ");
            int n1 = Integer.parseInt(str[0]);
            int n2 = Integer.parseInt(str[2]);
            int n3 = str[4].equals("X") ? 0 : Integer.parseInt(str[4]);
            
            int[] ar = new int[9];
            
            ar[0] = n1 % 10;
            ar[1] = n1 / 10;

            ar[3] = n2 % 10;
            ar[4] = n2 / 10;

            ar[6] = n3 % 10; 
            ar[7] = (n3 % 100) / 10;
            ar[8] = n3 / 100; 
            
            for(int n : ar) max = Math.max(max, n);      
        }
        max++;
        for(int i=0; i<max; i++) pns[i] = 1; // max미만의 i진수는 의미 없음
        
        for(String input : expressions) {
            String[] str = input.split(" ");
            if(str[4].equals("X")) continue;
 
            int n1 = Integer.parseInt(str[0]);
            String o = str[1];
            int n2 = Integer.parseInt(str[2]);
            int res = Integer.parseInt(str[4]);
            
            getPns(n1, n2, res, o);
        }
        
        for(String input : expressions) {
            String[] str = input.split(" ");
            if(!str[4].equals("X")) continue;
 
            int n1 = Integer.parseInt(str[0]);
            String o = str[1];
            int n2 = Integer.parseInt(str[2]);
            
            getAnswer(n1, n2, o);
        }
        
        String[] result = new String[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }
    
    public void getAnswer(int n1, int n2, String o) {
        Set<String> answerSet = new HashSet<>();
        String res = null;
        int[] ar = new int[6];
        
        ar[0] = n1 % 10;
        ar[1] = n1 / 10;
        
        ar[3] = n2 % 10;
        ar[4] = n2 / 10;
        
        for(int i=2; i<10; i++) {
            if(pns[i] != 1) { // i진법 가능 
                int tmp = 0;
                int p1 = 0;
                int p2 = 0;

                for(int j=0; j<3; j++) {
                    p1 += Math.pow(i, j) * ar[j];
                    p2 += Math.pow(i, j) * ar[j+3];
                }
                
                if(o.equals("+")) tmp = p1 + p2;
                else tmp = p1 - p2; 
                
                // 10진법 -> i진법
                res = Integer.toString(tmp, i);
                answerSet.add(res);
            }
        }
        if(answerSet.size() == 1) {
            answer.add(n1 +" "+ o +" "+ n2 + " = " + res);
        } else {
            answer.add(n1 +" "+ o +" "+ n2 + " = " + "?");
        }
    }
    
    public void getPns(int n1, int n2, int res, String o) {
        int[] ar = new int[9]; // 012 345 678
        
        ar[0] = n1 % 10;
        ar[1] = n1 / 10;
        
        ar[3] = n2 % 10;
        ar[4] = n2 / 10;
        
        ar[6] = res % 10; 
        ar[7] = (res % 100) / 10;
        ar[8] = res / 100;
        
        for(int i=max; i<10; i++) {
            if(pns[i] == 1) continue;
            
            int p1 = 0;
            int p2 = 0;
            int pres = 0;
            
            for(int j=0; j<3; j++) { // i진법 -> 10진법
                p1 += Math.pow(i, j) * ar[j];
                p2 += Math.pow(i, j) * ar[j+3];
                pres += Math.pow(i, j) * ar[j+6];
            }
            
            if(o.equals("+") && p1 + p2 == pres || o.equals("-") && p1 - p2 == pres) {
                pns[i] = 2;
            } else {
                pns[i] = 1;
            }
        }
    }
    
}