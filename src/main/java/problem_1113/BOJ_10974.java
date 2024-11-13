package problem_1113;

import java.util.*;
import java.io.*;

public class BOJ_10974 {
  static int n;
  static int[] ans;
  static boolean[] check;
  static StringBuilder sb = new StringBuilder();
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    ans = new int[n];
    check = new boolean[n];

    dfs(0);
    System.out.println(sb);
  }

  public static void dfs(int depth){
    if(depth == n){
      for(int i = 0; i < n; i++){
        sb.append(ans[i]).append(" ");
      }
      sb.append("\n");
      return;
    }

    for(int i = 0; i < n; i++){
      if(!check[i]){
        check[i] = true;
        ans[depth] = i+1;
        dfs(depth+1);
        check[i] = false;
      }
    }
  }
}
