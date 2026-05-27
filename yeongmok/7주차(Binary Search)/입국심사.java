import java.util.*;

class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        Long T=(long) times[0]*n;
        Long answer = binary(n,T,times);
        
        return answer;
    }
    
    Long binary(int n,Long T, int[] times){
        Long low=0L;
        Long high=T;
        
        while(low<high){
            Long mid = (low+high)/2;
            Long p=0L;
            
            for(int i=0;i<times.length;i++){
                p+=mid/times[i];
            }
            if(p>=n){
                high=mid;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }
}