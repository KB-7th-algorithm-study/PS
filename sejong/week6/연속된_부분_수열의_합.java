import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k){
        int[] answer = new int[]{0, 1000001};
        
        // 부분합 배열 구하기
        int len = sequence.length;
        int[] sums = new int[len];
        sums[0] = sequence[0];
        for(int i = 1; i < len; i++) sums[i] = sums[i - 1] + sequence[i];
        
        // 투 포인터 알고리즘
        int s = 0;
        int e = 0;
        while(s <= e && e < len){
            int sum;
            if(s == 0) sum = sums[e];
            else sum = sums[e] - sums[s - 1];
            
            // 부분 수열의 합은 k
            if(sum == k){
                // 짧은 수열을 찾습니다
                if(e - s == answer[1] - answer[0]){
                    // 길이가 같을 경우 앞쪽에 나오는 수열을 찾습니다
                    if(s < answer[0]){
                        answer[0] = s;
                        answer[1] = e;
                    }
                }else if(e - s < answer[1] - answer[0]){
                    answer[0] = s;
                    answer[1] = e;
                }
                s++;
            }else if(sum > k) s++;
            else if(sum < k) e++;
        }
        
        return answer;
    }
}