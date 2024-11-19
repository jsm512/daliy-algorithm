package pb1119;

import java.util.*;
import java.io.*;

public class BOJ_11053 {

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int n = Integer.parseInt(br.readLine());

    int[] a = new int[n+1];
    int[] dp = new int[n+1];


    st = new StringTokenizer(br.readLine(), " ");
    for(int i = 1; i <= n; i++){
      a[i] = Integer.parseInt(st.nextToken());

      dp[i] = 1;
    }

    int max = 1;
    for(int i = 1; i <= n; i++){
      for(int j = 1; j < i; j++){
        if(a[i] > a[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
      }

      max = Math.max(max, dp[i]);
    }

    System.out.print(max);
  }
}
