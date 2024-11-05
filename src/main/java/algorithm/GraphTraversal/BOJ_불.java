package algorithm.GraphTraversal;

import java.util.*;
import java.io.*;

public class BOJ_불 {
    static int r,c;
    static char[][] fire_map;
    static char[][] j_map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int answer = 0;
    static boolean ans_flag = true;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        j_map = new char[r][c];
        fire_map = new char[r][c];
        for(int i = 0; i < r; i++){
            String str = br.readLine();
            for(int j = 0; j < c; j++){
                j_map[i][j] = str.charAt(j);
                fire_map[i][j] = str.charAt(j);
            }
        }
//        for(char[] x : map){
//            System.out.println(Arrays.toString(x));
//        }
        /*
        fire_map과 j_map을 동시에 움직임 -> 한번 움직이고 J와F가 겹치는 곳은
        F로 변경하고 -> J가 살아남아 있는지 확인
        -> 이후 J가 탈출하게 되면 Ok
         */
        while(true){
            fire_bfs();
            j_bfs();
            merge();
            if(check()){
                if(exitCheck()){
                    answer++;
                    break;
                }
            }else {
                ans_flag = false;
                break;
            }
            answer++;
        }

        if(!ans_flag) System.out.println("IMPOSSIBLE");
        else System.out.println(answer+1);
    }
    public static boolean exitCheck(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(i == 0 || i == r-1 || j == 0 || j == c-1){
                    if(j_map[i][j] == 'J') return true;
                }
            }
        }
        return false;
    }
    public static boolean check(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(j_map[i][j] == 'J'){
                    return true;
                }
            }
        }
        return false;
    }
    public static void merge(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(fire_map[i][j] == 'F'){
                    j_map[i][j] = 'F';
                }
            }
        }
//        System.out.println("fire : " + Arrays.deepToString(fire_map));
//        System.out.println("j_amp : " + Arrays.deepToString(j_map));
    }
    public static void fire_bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(fire_map[i][j] == 'F'){
                    q.offer(new int[]{i,j});
                }
            }
        }
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 0 && ny >= 0 && nx < r && ny < c){
                    if(fire_map[nx][ny] != '#' && fire_map[nx][ny] != 'F'){
                        fire_map[nx][ny] = 'F';
                    }
                }
            }
        }
    }
    public static void j_bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(j_map[i][j] == 'J'){
                    q.offer(new int[]{i,j});
                }
            }
        }
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx >= 0 && ny >= 0 && nx < r && ny < c){
                    if(j_map[nx][ny] != '#' && j_map[nx][ny] != 'F' && j_map[nx][ny] != 'J'){
                        j_map[nx][ny] = 'J';
                    }
                }

            }
        }
    }
}
