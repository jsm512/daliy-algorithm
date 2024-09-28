package algorithm.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ_랜선자르기 {
    /*
    이분탐색 -> 범위 1 ~ (주어진 막대 중 가장 긴 막대의 길이)
    범위 잘 보기
     */
    static int k, n;
    static int[] bar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        bar = new int[k];
        for(int i = 0; i < k; i++){
            bar[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bar);

        long left = 1;
        long right = bar[bar.length - 1];
        while(left <= right){
            long mid = (left + right) / 2;

            if(splitBarCnt(mid) < n){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        System.out.println((left+right) / 2);
    }
    public static long splitBarCnt(long mid){
        long cnt = 0;

        for(int i = 0; i < k; i++){
            cnt += bar[i] / mid;
//            System.out.println(cnt);
        }
        return cnt;
    }
}
