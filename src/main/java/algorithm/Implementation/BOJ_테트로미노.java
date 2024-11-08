package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_테트로미노 {
    static int n, m, answer;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0;  j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                visited[i][j] = true;
                dfs(i,j,1,map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int depth, int sum){
        if(depth == 4){
            answer = Math.max(answer, sum);
            return;
        }
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                if (depth == 2) {
                    dfs(x, y, depth + 1, sum + map[nx][ny]);
                }
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }
}
