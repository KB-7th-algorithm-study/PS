// https://school.programmers.co.kr/learn/courses/30/lessons/340212
// [프로그래머스] 퍼즐 게임 챌린지 : Java

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        // 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값
        // n * maxDiff 불가 -> 이분탐색 활용 n * log(maxDiff)
        
        int maxDiff = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }
        
        int lLevel = 1;
        int rLevel = maxDiff;
        
        while (lLevel < rLevel) {
            int mid = (lLevel + rLevel) / 2;
            if (calcTime(mid, diffs, times) <= limit) {
                rLevel = mid;
            }
            else {
                lLevel = mid + 1;
            }
        }
        
        return rLevel;
    }
    
    private long calcTime(int level, int[] diffs, int[] times) {
        long curTimeSum = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                curTimeSum += times[i];
            }
            else {
                curTimeSum += (long)(times[i] + times[i-1]) * (diffs[i] - level) + times[i];
            }
        }
        
        return curTimeSum;
    }
}