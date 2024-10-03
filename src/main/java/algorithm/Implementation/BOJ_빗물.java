package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_빗물 {
    static int answer = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < m; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < m - 1; i++){
            int left = 0;
            int right = 0;
//            System.out.print("idx : " + i + " ");
            for(int j = i-1; j >= 0; j--){
                left = Math.max(left, arr[j]);
            }
//            System.out.print("left : " + left + " ");

            for(int j = i + 1; j < m; j++){
                right = Math.max(right, arr[j]);
            }
//                System.out.print("right : " + right + " ");

            if(arr[i] < left && arr[i] < right) {
                answer += Math.min(left,right) - arr[i];
//                System.out.println("answer : " + answer);
            }
//            System.out.println();

        }

        System.out.println(answer);
    }

}
