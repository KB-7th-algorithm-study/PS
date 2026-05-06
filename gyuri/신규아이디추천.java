import java.util.*;
import java.io.*;

class Solution {
    public String solution(String new_id) {
        // 1단계: 모든 대문자를 소문자로 치환
        new_id = new_id.toLowerCase();

        // 2단계: 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거
        new_id = new_id.replaceAll("[^a-z0-9._-]", "");

        // 3단계: 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환
        new_id = new_id.replaceAll("[.]{2,}", ".");

        // 4단계: 마침표가 처음이나 끝에 위치한다면 제거
        new_id = new_id.replaceAll("^\\.|\\.$", "");

        // 5단계: 빈 문자열이라면 "a"를 대입
        if(new_id.equals("")) new_id = "a";

        // 6단계: 16글자 이상이면 15글자만 남기기
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("^\\.|\\.$", "");
        }

        // 7단계: 2자 이하라면 3이 될때까지 마지막 문자를 끝에 붙임
        if(new_id.length() <= 2) {
            while(new_id.length() < 3) {
                new_id += new_id.charAt(new_id.length()-1);
            }
        }

        return new_id;
    }
}