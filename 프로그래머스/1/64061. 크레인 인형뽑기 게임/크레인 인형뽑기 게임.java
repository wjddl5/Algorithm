import java.util.*;

class Solution {

    Stack<Integer> stack = new Stack<>();
    
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int high = board.length;
        
        for(int move : moves) {
            int row = 0;
            int col = move - 1;
            
            while(row < high) {
                if(board[row][col] > 0) {
                    int num = board[row][col];
                    if(crane(num)) answer += 2;
                    board[row][col] = 0;
                    break;
                }
                row++;
            }
        }
        return answer;
    }
    
    public boolean crane(int num) {
        if(stack.isEmpty() || stack.peek() != num) {
            stack.push(num);
            return false;
        }
        stack.pop();
        return true;
    }
}