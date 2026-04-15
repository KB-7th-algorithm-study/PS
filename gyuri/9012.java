import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            Deque<String> stack = new ArrayDeque<String>();
            String str = st.nextToken();
            String answer = "";

            for(int j = 0; j < str.length(); j++) {
                String s = String.valueOf(str.charAt(j));

                if(stack.isEmpty()) {
                    // 1. 스택에 아무것도 없을 때 ) 들어오면 NO
                    if(s.equals(")")) {
                        answer = "NO";
                        break;
                    } else { // 2. 스택에서 아무것도 없을 때 ( 들어오면 push
                        stack.push("(");
                    }
                } else if(s.equals("(")) {
                    stack.push("(");
                } else if(s.equals(")") && stack.peek().equals("(")) {
                    stack.pop();
                }
            }

            if(stack.isEmpty() && answer.isEmpty()) {
                answer = "YES";
            } else answer = "NO";

            System.out.println(answer);
        }
    }
}
