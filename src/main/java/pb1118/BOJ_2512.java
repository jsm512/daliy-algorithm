package pb1118;

import java.util.*;
import java.io.*;

public class BOJ_2512 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];

    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);

    int m = Integer.parseInt(br.readLine());

    int left = 0, right = arr[arr.length - 1];
    while (left <= right) {
      int mid = (left + right) / 2;
      long ans = 0;

      for (int i = 0; i < n; i++) {
        if (arr[i] > mid) {
          ans += mid;
        } else {
          ans += arr[i];
        }
      }
      if (ans <= m) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    System.out.println(right);
  }
}
