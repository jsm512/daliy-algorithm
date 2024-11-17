package pb1118;

import java.util.*;
import java.io.*;

public class BOJ_7511 {

  static int[] parent;
  static StringBuilder sb = new StringBuilder();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
      sb.append("Scenario ").append(t).append(":").append("\n");
      int n = Integer.parseInt(br.readLine());
      int k = Integer.parseInt(br.readLine());

      parent = new int[n];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = i;
      }

      for (int i = 0; i < k; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        union(a, b);
      }
      int m = Integer.parseInt(br.readLine());
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (find(a) == find(b)) {
          sb.append(1).append("\n");
        } else {
          sb.append(0).append("\n");
        }
      }
      sb.append("\n");
    }

    System.out.print(sb);
  }

  public static boolean union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x == y) {
      return false;
    }
    if (x <= y) {
      parent[y] = x;
    } else {
      parent[x] = y;
    }
    return true;
  }

  public static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    return find(parent[x]);
  }
}
