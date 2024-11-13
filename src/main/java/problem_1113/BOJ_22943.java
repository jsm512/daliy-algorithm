package problem_1113;

import java.util.*;
import java.io.*;
public class BOJ_22943 {

  /*
  조건 1. 서로 다른 소수의 합으로 나타낼 수 있는 숫자인가?
  조건 2. 생성된 숫자 중 M으로 나눈 수가 두 개의 소수의 곱으로 표현되는가?
  조건 1, 조건 2를 동시에 만족하는 숫자의 개수
  
  0~9까지 사용해 K자리수의 숫자 조합 (첫자리 0은 제외)
  구해진 숫자 조합에서 조건 1, 조건 2를 만족하는 숫자의 개수 출력
   */
  static int k, m;
  static boolean[] check = new boolean[10];
  static boolean[] isPrime;
  static ArrayList<Integer> prime_num = new ArrayList<>();
  static HashSet<Integer> prime_sum = new HashSet<>();
  static HashSet<Integer> prime_multi = new HashSet<>();

  static ArrayList<Integer> arr = new ArrayList<>();
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    k = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    int max = (int) Math.pow(10,k);
    isPrime = new boolean[max];

    eratoss(max);
    sumOfPrime();
    multiOfPrime(max);
    dfs(0, "");
//    System.out.print(arr);

    int ans = 0;
    for(int num : arr){
      if(check1(num) && check2(num)){
        ans++;
      }
    }

    System.out.println(ans);

  }
  public static void dfs(int depth, String str){
    if(depth == k){
      if(str.charAt(0) != '0'){
        arr.add(Integer.parseInt(str));
      }
      return;
    }
    for(int i = 0; i <= 9; i++){
      if(!check[i]){
        check[i] = true;
        dfs(depth+1, str+i);
        check[i] = false;
      }
    }
  }

  public static void eratoss(int max){
    isPrime[0] = isPrime[1] = true;
    for(int i = 2; i*i < max; i++){
      if(!isPrime[i]){
        for(int j = i * i; j < max; j += i){
          isPrime[j] = true;
        }
      }
    }

    for(int i = 2; i < max; i++){
      if(!isPrime[i]) prime_num.add(i);
    }
  }

  public static void sumOfPrime(){
    for(int i = 0; i < prime_num.size(); i++){
      for(int j = i + 1; j < prime_num.size(); j++){
        int sum = prime_num.get(i) + prime_num.get(j);
        if(sum < Math.pow(10,k)){
          prime_sum.add(sum);
        }
      }
    }
  }

  public static void multiOfPrime(int max){
    for(int i = 0; i < prime_num.size(); i++){
      for(int j = i; j < prime_num.size(); j++){
        long multi = (long) prime_num.get(i) * prime_num.get(j);
        if(multi < max){
          prime_multi.add((int)multi);
        }
      }
    }
  }

  public static boolean check1(int n){
    return prime_sum.contains(n);
  }

  public static boolean check2(int n){
    while(n % m == 0){
      n /= m;
    }
    return prime_multi.contains(n);
  }
}
