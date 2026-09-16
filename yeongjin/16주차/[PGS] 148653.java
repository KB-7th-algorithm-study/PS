import java.util.*;
class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey/10 != 0){
            int n = storey%10;
            if(n <5){
                storey -=n;
                answer +=n;
            }else if(n >5){
                storey +=10-n;
                answer +=10-n;
            }else{
                storey /= 10;
                n = storey%10;
                if(n >=5){
                    storey +=1;
                    
                }
                answer +=5;
                
                continue;
            }
            storey /=10;
        }
        
        if(storey <=5){
            answer += storey;
        }else {
            answer += 10-storey+1;
        }

        
        return answer;
    }
}