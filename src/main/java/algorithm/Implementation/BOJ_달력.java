package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_달력 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[366];

        int min_value = Integer.MAX_VALUE;
        int max_value = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            min_value = Math.min(min_value, s);
            max_value = Math.max(max_value, e);

            for(int j = s; j <= e; j++){
                arr[j]++;
            }
        }
//        for(int i = min_value; i<= max_value; i++){
//            System.out.print(arr[i] + " ");
//        }

        int ans = 0;
        int cnt = 0;
        int max = 0;
        for(int i = min_value; i <= max_value; i++){
            max = Math.max(max, arr[i]);
            cnt++;
            if(arr[i] == 0){
                ans += (cnt-1) * max;
                cnt = max = 0;
            }
            if(i == max_value){
                ans += cnt * max;
            }

        }

        System.out.println(ans);
    }
}
