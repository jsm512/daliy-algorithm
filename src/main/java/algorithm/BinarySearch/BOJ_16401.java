package algorithm.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ_16401 {
  /*
  1 2 3 4 5 6 7 8 9 10 과자가 있을 때
  3명한테 줄 수 있는 최대 과자의 길이는
  8 9 10 임

   */
  static int m, n;
  static int[] cookies;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());

    cookies = new int[n];
    long right = 0;
    st = new StringTokenizer(br.readLine(), " ");
    for(int i = 0; i < n; i++){
      cookies[i] = Integer.parseInt(st.nextToken());
      right = Math.max(right, cookies[i]);
    }

    long left = 1;

    while(left <= right){
      int cnt = 0;
      long mid = (left + right) / 2;

      for(int i = 0; i < n; i++) {
        cnt += cookies[i] / mid;
      }

      if(cnt < m){
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }

    System.out.println(left - 1);
  }
}
