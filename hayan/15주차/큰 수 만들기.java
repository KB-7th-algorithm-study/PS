import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> dq = new ArrayDeque<>();
        
        for(char c : number.toCharArray()){
            if(dq.isEmpty())
                dq.addLast(c);
            else{
                while(dq.peekLast()<c && k>0){
                    dq.pollLast();
                    k--;
                    if(dq.isEmpty()) break;
                }
                dq.addLast(c);
            }
        }
        if(k>0){ //k가 남아있을때 (ex. 98765)
            while(k>0){
                dq.pollLast();
                k--;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(char c : dq){
            sb.append(c);
        }
        
        return sb.toString();
    }
}