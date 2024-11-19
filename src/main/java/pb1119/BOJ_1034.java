package pb1119;

import java.util.*;
import java.io.*;

public class BOJ_1034 {
  static int n,m;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());


    int[] lightOfRow = new int[n];
    String[] line = new String[n];

    for(int i = 0; i < n; i++){
      String str = br.readLine();
      int cnt = 0;
      for(int j = 0; j < m; j++){
        if(str.charAt(j) == '0')cnt++;
      }

      lightOfRow[i] = cnt;
      line[i] = str;
    }
    int k = Integer.parseInt(br.readLine());

    int max = 0;

    for(int i = 0; i < n; i++){

      if(lightOfRow[i] <= k && (k - lightOfRow[i]) % 2 == 0){

        int cnt = 1;

        for(int j = 0; j < n; j++){
          if(i == j) continue;
          if(line[i].equals(line[j])) cnt++;
        }

        max = Math.max(max, cnt);
      }
    }

    System.out.println(max);
  }
}
