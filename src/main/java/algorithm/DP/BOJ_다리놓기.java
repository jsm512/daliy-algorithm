package algorithm.DP;

import java.util.*;
import java.io.*;

public class BOJ_다리놓기 {
    static int[][] dp = new int[31][31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test = 0; test < T; test++){
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(conbination(m,n)).append("\n");
        }
        System.out.println(sb.toString());
    }
    public static int conbination(int n, int r){
        if(dp[n][r] > 0) return dp[n][r];
        else if(n == r || r == 0) return dp[n][r] = 1;
        else{
            return dp[n][r] = conbination(n-1, r - 1) + conbination(n-1, r);
        }
    }
}
