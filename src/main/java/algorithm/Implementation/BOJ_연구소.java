package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_연구소 {
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int answer = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(answer);
    }
    public static void dfs(int depth){
        if(depth == 3){
            bfs();
            return;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    dfs(depth+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 2){
                    q.offer(new int[]{i,j});
                }
            }
        }
        int[][] copy = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                copy[i][j] = map[i][j];
            }
        }

        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                    if(copy[nx][ny] == 0){
                        q.offer(new int[]{nx,ny});
                        copy[nx][ny] = 2;
                    }
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(copy[i][j] == 0) cnt++;
            }
        }
        answer = Math.max(answer, cnt);
    }
}
