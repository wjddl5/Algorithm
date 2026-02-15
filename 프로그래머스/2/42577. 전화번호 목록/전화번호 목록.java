import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<phone_book.length; i++) {
            map.put(phone_book[i], i);
        }
        
        for(int i=0; i<phone_book.length; i++) {
            String str = phone_book[i];
            String[] ar = str.split("");
            StringBuilder sb = new StringBuilder();
            
            for(String s : ar) {
                sb.append(s);            
                int j = map.getOrDefault(sb.toString(), -1);
                if(j > -1 && j != i) {
                    return false;
                }    
            }         
        }
        
        return true;
    }
}