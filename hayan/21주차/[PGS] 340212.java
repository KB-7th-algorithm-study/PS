import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = diffs[0];
        
        for(int diff: diffs){
            right = Math.max(right, diff);
        }
        
        while(left < right){
            int mid = left + (right - left) / 2;
            
            long total =0; //현재 숙련도 mid로 모든 퍼즐을 해결하는 데 걸리는 시간
            for(int i=0; i<diffs.length; i++){
                if(diffs[i] <= mid){
                    total += times[i];
                }else{
                    int prev = times[i-1];
                    //a 틀리는 횟수 : diffs[i]-mid
                    //b 한 번 틀릴때 사용 시간 : times[i] + prev
                    // a*b + times[i]
                    total += (long)(diffs[i]-mid)*(times[i] + prev) + times[i];
                }
            }
            
            if(total <= limit){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        
        return left;
    }
}