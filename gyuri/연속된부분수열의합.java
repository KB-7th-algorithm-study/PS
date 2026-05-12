import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int start = 0; // 부분 수열의 시작 인덱스
        int finish = 0; // 부분 수열 끝 인덱스
        int min = Integer.MAX_VALUE; // 가장 짧은 길이를 저장

        int tot = sequence[0]; // 현재 부분 수열의 합

        int answerStart = 0; // 정답 시작 인덱스 저장
        int answerFinish = 0; // 정답 끝 인덱스 저장

        // 투 포인터 탐색
        while(finish < sequence.length) {
            // 부분 수열의 합이 k와 같은 경우
            if(tot == k) {
                // 현재 부분 수열 길이가 더 짧은 경우
                if(finish - start < min) {
                    min = finish - start; // 최소 길이 갱신

                    // 정답 인덱스 저장
                    answerStart = start;
                    answerFinish = finish;
                }
            }

            // 합이 k 이상이면 왼쪽 값을 제거해 합 줄이기
            if(tot >= k) {
                tot -= sequence[start];
                start++;
            } else {
                // 합이 k보다 작으면 오른쪽 값 추가해 합 늘리기
                finish++;

                // 배열 범위 체크
                if(finish < sequence.length) {
                    tot += sequence[finish];
                }
            }
        }

        // 시작 인덱스와 끝 인덱스 반환
        return new int[]{answerStart, answerFinish};
    }
}