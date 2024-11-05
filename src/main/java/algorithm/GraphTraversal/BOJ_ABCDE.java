package algorithm.GraphTraversal;

import java.io.*;
import java.util.*;

public class BOJ_ABCDE {

  static int n, m;
  static ArrayList<Integer>[] arr;
  static boolean[] visited;
  static int ans = 0;


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new ArrayList[n];
    visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      arr[i] = new ArrayList<>();
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      arr[s].add(e);
      arr[e].add(s);
    }

    for (int i = 0; i < n; i++) {
      if (ans == 0) {
        dfs(i, 1);
      }
    }
    System.out.println(ans);
  }

  public static void dfs(int start, int depth) {
    System.out.println(start + " " + depth);
    if (depth == 5) {
      ans = 1;
      return;
    }

    visited[start] = true;
    for (int i : arr[start]) {
      if (!visited[i]) {
        dfs(i, depth + 1);
      }
    }
    visited[start] = false;
  }
}
