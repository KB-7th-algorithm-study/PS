class Solution {
    
    public boolean f(int[] diffs, int[] times, long limit, int lev){
        
        int time_prev = 0;
        long time_sum = 0;
        for(int i =0; i<diffs.length; i++){
            int time_cur = times[i];
            int cnt = diffs[i] - lev;
            if(cnt >0){
                time_sum += ((long)time_cur+time_prev)*cnt+time_cur;
            }else{
                time_sum += time_cur;
            }
            time_prev = time_cur;
            
            if(time_sum > limit) return false;
        }
        
        return true;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        int right = 0;
        
        for(int i : diffs){
            if(i > right) right = i; 
        }
        
        int left = 1;
     
        while(left <= right){
            int mid = left + (right-left)/2;
            if(f(diffs,times,limit,mid) == false){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        
        return left;
    }
}