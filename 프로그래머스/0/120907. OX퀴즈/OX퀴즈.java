class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for(int i=0; i<quiz.length; i++) {
            String q = quiz[i];
            String[] str = q.split(" ");
            
            int n1 = Integer.parseInt(str[0]);
            int n2 = Integer.parseInt(str[2]);
            int res = Integer.parseInt(str[4]);
            
            switch(str[1]) {
                case "+":
                    if(n1+n2 == res) answer[i] = "O";
                    else answer[i] = "X";
                    break;
                case "-":
                    if(n1-n2 == res) answer[i] = "O";
                    else answer[i] = "X";
                    break;
            }
        }
        
        return answer;
    }
}