package algorithm.DP;

import java.util.*;
import java.io.*;

public class BOJ_계단오르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        for(int i = 0; i < n; i++){
            cost[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];
        dp[0] = cost[0];
        for(int i = 1; i < n; i++){
            if(i == 1) dp[i] = cost[0] + cost[1];
            else if(i == 2) dp[i] = Math.max(cost[0], cost[1]) + cost[i];
            else{
                dp[i] = Math.max(dp[i - 3] + cost[i - 1], dp[i - 2]) + cost[i];
            }
        }

        System.out.println(dp[n-1]);

    }
}
