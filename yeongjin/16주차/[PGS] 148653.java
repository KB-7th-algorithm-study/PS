import java.util.*;
class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        String s = Integer.toString(storey);
        int n = s.length();
    
        for(int i =0; i<n; i++){
            int a =s.charAt(i) - '0';
            if(a <=5){
                answer +=a;
            }else{
                answer += 10-a +1;
            }
        }
        
        return answer;
    }
}