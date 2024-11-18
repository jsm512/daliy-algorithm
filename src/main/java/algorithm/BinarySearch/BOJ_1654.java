package algorithm.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ_1654 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    int k = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());

    int[] lan = new int[k];
    long right = 0;
    for (int i = 0; i < k; i++) {
      lan[i] = Integer.parseInt(br.readLine());
      right = Math.max(right, lan[i]);
    }

    long left = 1;
    while (left <= right) {
      long mid = (left + right) / 2;
      int cnt = 0;

      for (int i = 0; i < k; i++) {
        cnt += lan[i] / mid;
      }
      if(cnt < n){
        right = mid - 1;
      }else{
        left = mid + 1;
      }

//      System.out.println("막대기 수 : " + cnt + " left : " + left + " right : " + right);
    }

    System.out.println(left - 1);
  }
}
