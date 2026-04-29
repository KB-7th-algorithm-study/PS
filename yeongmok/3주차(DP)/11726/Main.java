import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] Dp = new int[N+1];

        for(int i=1;i<=N;i++){
            if(i<3)
                Dp[i] = i;
            else
                Dp[i] = (Dp[i-1]+Dp[i-2])%10007;
        }

        System.out.println(Dp[N]%10007);
    } 
}
