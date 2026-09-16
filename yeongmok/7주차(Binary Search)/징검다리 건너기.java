import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int[] stones_copy = stones.clone();
        Arrays.sort(stones_copy);
        int answer = binary(stones_copy[0],stones_copy[stones_copy.length-1],stones,k);
        return answer;
    }
    
    int binary(int min, int max,int[] stones,int k){
        int low = min;
        int high = max;

        while(low<high){
            int mid = (low+high)/2;
            int p=0;
            for(int i=0;i<stones.length;i++){
                if(p>=k)
                    break;
                else if(stones[i]-mid<=0){
                    p++;
                }
                else{
                    p=0;
                }
            }
            if(p>=k){
                high=mid;
            }
            else{
                low=mid+1;
            }
        }
        return low;
    }
}