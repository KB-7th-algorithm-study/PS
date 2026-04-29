import java.util.*;

// 81.5점
class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        
        // 조이스틱 조작 횟수 = 수평 이동 횟수 + 수직 이동 횟수
        
        // 수평 이동 횟수
        // EX) ZZA(2)AAA(5)ZZZZ : A는 수직 이동이 필요 없다. 방문 자체를 안하는게 최선
        // 1. A 덩어리가 제일 큰 구역의 시작점, 끝점을 구한다 EX: (2, 5)
        // 2. 시작점 방문 CASE (109876), 끝점 방문 CASE(987678901), 순차 방문 CASE(123456789) 전부 구한다
        // 2-1. [0 -> s(s - 1)] + [s -> 0 (s - 1)] + 1(오른쪽 이동) + [n -> e (n - e - 1)] = n + 2*s - 2 - e
        // 2-2. [0 -> e (1 + n - e - 1)] + [e -> 0 (n - e - 1 + 1)] + [0 -> s (s - 1)] = 2n - 2e + s - 1
        // 2-3. n
        // 3. 2번에서 구한 값중 최솟값이 수평 이동 횟수의 최솟값이다.
        int ts = 0; // 임시 시작점
        int s = 0; // 최대 덩어리 시작점
        int tl = 0; // 임시 길이
        int l = 0; // 최대 덩어리 길이
        for(int i = 0; i < len; i++){
            char C = name.charAt(i);
            // A일 때
            if(C == 'A'){
                if(tl == 0){
                    ts = i;
                    tl++;
                }else if(i > 0){
                    char C2 = name.charAt(i - 1);
                    if(C2 == 'A'){
                        tl++;
                    }
                }
            }else{ // A가 아닐 때
                if(tl > l){
                    l = tl;
                    s = ts;
                    tl = 0;
                }
            }
        }
        if(tl > l){
            l = tl;
            s= ts;
        }
        
        int h; // 수평 조작 횟수
        int n = len - 1;
        int e = s + l - 1; // 끝점
        // A가 없다면 수평 조작 횟수는 n
        if(l == 0){
            h = n;
        }else if(n == e){
            h = 0;
        }else{
            int c1 = n + 2*s - 2 - e; // CASE1
            int c2 = 2*n - 2*e + s - 1; // CASE2
            int c3 = n; // CASE3
            // CASE중 최솟값 구하기
            h = Math.min(c1, c2);
            h = Math.min(h, c3);
        }
        System.out.println(s + "," + e);
        System.out.println(h);
        answer+=h;
        
        // 수직 이동 횟수
        // 아스키 코드 값 A - 65, Z - 90
        // 임의의 알파벳 - A = 0 ~ 25 | 중앙값 13을 기준으로 방향이 정해짐
        int A = 65;
        for(int i = 0; i < len; i++){
            int T = (int) name.charAt(i);
            if(T - A < 13) answer += T - A;
            else answer += 26 - (T - A); // 25 - (T - A) + 1
        }
        System.out.println(answer);
        
        return answer;
    }
}