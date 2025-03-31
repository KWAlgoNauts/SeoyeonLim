import java.io.*;
import java.util.*;

public class M1021 {
    public static void main(String[] args) throws IOException {
        //회전을 하는 거니깐 나눗셈 정리도 한 번 생각해볼것
        //2번, 3번의 연산의 최솟값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sb = new StringTokenizer(br.readLine());

        Deque<Integer> deque = new LinkedList<>(); //값 저장

        //Queue<Integer> index = new LinkedList<>(); //index 저장할 곳
        int move = 0; // 이동 수

        //입력
        int N = Integer.parseInt(sb.nextToken());//큐의 크기
        int M = Integer.parseInt(sb.nextToken()); //수의 개수 (50 이하)
        int arr[] = new int[M];

        sb = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            arr[i] = Integer.parseInt(sb.nextToken());
        }

        for(int i = 0; i < N; i++){
            deque.add(i + 1);
        }

        //로직
        for(int i = 0; i < M; i++){
            //System.out.println(i);
            //index가 1인지 확인하기
            if(arr[i] == 1){
                deque.pollFirst();
                for(int j = (i + 1); j < M; j++){
                    arr[j] = arr[j] - 1;
                    //System.out.print("arr["+j+"] = " + arr[j] + ", ");
                }
                continue;
            }else{
//                System.out.println("(deque.size() - arr[i]) : " + (deque.size() - arr[i] + 1));
//                System.out.println("arr[i]-1 : " + (arr[i] - 1));

                //index가 왼쪽 오른쪽 중 가까운 곳을 결정하기
                int min = Math.min((deque.size() - arr[i]) + 1, (arr[i] - 1)) ;
                int dec = (deque.size() - arr[i]) < (arr[i] - 1) ? 0 : 1;

                //차이만큼 이동 횟수 업데이트
                //뒤로 가기
                if(dec == 0){
//                    System.out.println("뒤로가기");
                    for(int j = 0 ; j < min; j++){
                        int last = deque.pollLast();
                        deque.offerFirst(last);
//                        System.out.println("last : " + last +", deque.first : " + deque.peek());
                    }
                    int Q = deque.pollFirst();
//                    System.out.println("Q : " + Q);
                    for(int j = (i + 1); j < M; j++) {
                        int m = (arr[j] + min) % (deque.size()+ 1) -1;
//                        System.out.println("움직일 곳" + ((arr[j] + min) % (deque.size() + 1) - 1));
                        if((m + 1) == 0){
                            arr[j] = deque.size();
                        }else{
                            arr[j] = m;
                        }
                    }
                }else{
//                    System.out.println("앞으로 가기");
                    //앞으로 가기
                    for(int j = 0 ; j < min; j++){
                        int first = deque.pollFirst();
                        deque.offerLast(first);
                    }
                    int Q = deque.pollFirst();
//                    System.out.println("Q : " + Q);
                    for(int j = (i + 1); j < M; j++) {
                        int m = (arr[j] - min + (deque.size() + 1)) % (deque.size() + 1) - 1;
//                        System.out.println("움직일 곳" + m );
                        if ((m + 1) == 0){
                            arr[j] = deque.size();
                        }
                        else{
                            arr[j] = m;
                        }
                    }
                }
                move += min;
            }
        }
        System.out.println(move);
    }
}