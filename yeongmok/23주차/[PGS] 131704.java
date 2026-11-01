import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        Stack<Integer> assistBelt = new Stack<>();
        
        for(int i= 1; i<=order.length; i++){
            assistBelt.push(i);
            while(!assistBelt.empty() && order[answer] == assistBelt.peek()){
                answer++;
                assistBelt.pop();
            }
        }
        return answer;
    }
}