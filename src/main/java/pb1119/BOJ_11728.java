package pb1119;

import java.util.*;
import java.io.*;

public class BOJ_11728 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[] a = new int[n];
    int[] b = new int[m];

    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < n; i++) {
      a[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < m; i++) {
      b[i] = Integer.parseInt(st.nextToken());
    }

    int[] sum = new int[n + m];

    int idx = 0;
    int left = 0;
    int right = 0;

    while (left < n && right < m) {
      if(a[left] <= b[right]){
        sb.append(a[left++] + " ");
      }else{
        sb.append(b[right++] + " ");
      }
    }

    if(left == n){
      for(int i = right; i < m; i++){
        sb.append(b[i] + " ");
      }
    }
    if(right == m){
      for(int i = left; i < n; i++){
        sb.append(a[i] + " ");
      }
    }
    System.out.print(sb);
  }
}
