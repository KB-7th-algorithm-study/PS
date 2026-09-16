import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i =0; i<number.length(); i++){
            char c = number.charAt(i);
            if(stack.isEmpty()) {
                stack.addLast(c); 
                continue;
            }
            while(!stack.isEmpty() && stack.peekLast()<c && k>0){
                k--;
                stack.removeLast();
            }
            stack.addLast(c);
        }
        while(k>0){
            stack.removeLast();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!stack.isEmpty()){
            sb.append(stack.removeFirst());
        }
            
        return sb.toString();
    }
}