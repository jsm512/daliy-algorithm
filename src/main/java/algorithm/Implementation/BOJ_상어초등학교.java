package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_상어초등학교 {
    public static class Point implements Comparable<Point>{
        int x;
        int y;
        int blank;
        int like;
        public Point(int x, int y, int blank, int like){
            this.x = x;
            this.y = y;
            this.blank = blank;
            this.like = like;
        }

        @Override
        public int compareTo(Point o) {
            if(this.like > o.like) return -1;
            else if(this.like < o.like) return 1;
            else{
                if(this.blank > o.blank) return -1;
                else if(this.blank < o.blank) return 1;
                else{
                    if(this.x > o.x) return 1;
                    else if(this.x < o.x) return -1;
                    else{
                        if(this.y > o.y) return 1;
                        else return -1;
                    }
                }
            }
        }
    }
    static int n;
    static int[][] seats;
    static int[] student;
    static List<Integer>[] list;
    static PriorityQueue<Point> q = new PriorityQueue<>();
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        seats = new int[n+1][n+1];
        student = new int[n*n+1];
        list = new ArrayList[n*n+1];

        for(int i = 1; i <= n*n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n*n; i++){
            String[] s = br.readLine().split(" ");
            student[i] = Integer.parseInt(s[0]);

            for(int j = 1; j < s.length; j++){
                list[student[i]].add(Integer.parseInt(s[j]));
            }
        }

        for(int i = 1; i <= n*n; i++){
            sol(student[i]);
            q.clear();
        }

        int ans = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                ans += getLike(i,j);
            }
        }
        System.out.println(ans);
    }
    public static int getLike(int x, int y){
        int num = seats[x][y];
        int nx, ny, like = 0;
        for(Integer now : list[num]){
            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx >= 1 && ny >= 1 && nx <= n && ny <= n){
                    if(seats[nx][ny] == now) like++;
                }
            }
        }
        switch (like){
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 10;
            case 3:
                return 100;
            default:
                return 1000;

        }
    }

    public static void sol(int student){
        int nx, ny;
        int blank, like;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                blank = 0;
                like = 0;
                if(seats[i][j] != 0) continue;

                for(int k = 0; k < 4; k++){
                    nx = i + dx[k];
                    ny = j + dy[k];
                    if(nx >= 1 && ny >= 1 && nx <= n && ny <= n){
                        for(Integer n : list[student]){
                            if(n == seats[nx][ny]) like++;
                        }
                        if(seats[nx][ny] == 0) blank++;
                    }
                }
                q.offer(new Point(i,j,blank,like));
            }
        }

        Point tmp = q.poll();
        seats[tmp.x][tmp.y] = student;
    }
}
