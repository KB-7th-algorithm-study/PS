import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        Deque<int[]> dq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < num; i++) {
            int val = Integer.parseInt(st.nextToken());
            dq.add(new int[]{i+1, val});
        }

        int[] info = new int[2];
        int idx = 0;

        if(!dq.isEmpty()) {
            info = dq.poll();
            idx = info[1];
        }
        System.out.print(info[0] + " ");

        while(!dq.isEmpty()) {
            if(idx > 0) {
                for(int i = 1; i < idx; i++) {
                    dq.add(dq.poll());
                }
            } else {
                idx = Math.abs(idx);

                for(int i = 0; i < idx; i++) {
                    dq.addFirst(dq.pollLast());
                }

            }
            if(!dq.isEmpty()) {
                info = dq.poll();
                idx = info[1];
            }
            System.out.print(info[0] + " ");
        }
    }
}