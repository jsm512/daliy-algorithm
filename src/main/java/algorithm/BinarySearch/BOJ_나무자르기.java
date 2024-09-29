package algorithm.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ_나무자르기 {
    static int n;
    static long m;
    static long[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        trees = new long[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);

        long left = 1;
        long right = trees[n-1];

        while(left <= right){
            long mid = (left + right) / 2;

            if(split(mid) < m){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        System.out.println(right);
    }
    public static long split(long mid){
        long cnt = 0;

        for(int i = 0; i < trees.length; i++){
            if(trees[i] > mid){
                cnt += (trees[i] - mid);
            }
        }

        return cnt;
    }
}
