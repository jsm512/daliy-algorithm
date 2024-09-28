package kr.co.ureca.GraphTraversal;

import java.util.*;
import java.io.*;
public class BOJ_숨바꼭질 {
    static int n, k;
    static int[] location = new int[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if(n == k) System.out.println(0);
        else System.out.println(bfs(n));

    }

    public static int bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        location[start] = 1;

        while(!q.isEmpty()){
            int now = q.poll();
            for(int i = 0; i < 3; i++){
                int next = 0;
                if(i == 0) next = now + 1;
                else if(i == 1) next = now - 1;
                else next = now * 2;

                if(next == k) return location[now];
                if(next >= 0 && next < location.length && location[next] == 0){
                    q.add(next);
                    location[next] = location[now] + 1;
                }
            }
        }
        return 0;
    }
}
