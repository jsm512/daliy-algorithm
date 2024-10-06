package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_인구이동 {
    public static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n,l,r;
    static int[][] map;
    static boolean[][] check;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<Node> list;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());
    }

    public static int move(){
        int ans = 0;
        //인구이동이 가능할 때까지 반복
        while(true){
            //종료를 위한 boolean
            boolean flag = false;
            //인구이동 후 방문 초기화
            check = new boolean[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!check[i][j]){
                        int sum = bfs(i,j);
                        if(list.size() > 1){
                            movePopulation(sum);
                            flag = true;
                        }
                    }
                }
            }
            if(!flag) return ans;
            ans++;
        }
    }
    public static int bfs(int x, int y){
        Queue<Node> q = new ArrayDeque<>();
        list = new ArrayList<>();
        q.offer(new Node(x,y));
        list.add(new Node(x,y));
        check[x][y] = true;

        int sum = map[x][y];
        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                    if(!check[nx][ny]){
                        int diff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);
                        if(l <= diff && diff <= r){
                            q.offer(new Node(nx,ny));
                            list.add(new Node(nx,ny));
                            sum += map[nx][ny];
                            check[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return sum;
    }

    public static void movePopulation(int sum){
        int avg = sum / list.size();
        for(Node n : list){
            map[n.x][n.y] = avg;
        }
    }
}
