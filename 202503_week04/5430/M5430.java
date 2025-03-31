import java.io.*;
import java.util.*;

public class M5430{
    public static void main(String[] args) throws IOException{
        //deque 사용하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        try {//테스트 반복하기
            while (T-- > 0) {
                Deque<Integer> deque = new ArrayDeque<>();

                String p = br.readLine();
                int n = Integer.parseInt(br.readLine());

                String arr = br.readLine();

                if(n == 0){
                    if(p.contains("D")){
                        sb.append("error\n");
                    }else{
                       sb.append("[]\n");
                    }
                    continue;
                }

                arr = arr.substring(1,arr.length()-1);
                String[] parts = arr.split(",");

                //숫자 넣기
                for (String part : parts) {
                    deque.add(Integer.parseInt(part));
                }

                //p 의 길이 만큼 반복
                int length = p.length();
                boolean isReversed = false;
                boolean error = false;
                for (int i = 0; i < length; i++) {
                    //뒤집기
                    if (p.charAt(i) == 'R') {
                        isReversed = !isReversed;
                    }
                    //버리기
                    else if (p.charAt(i) == 'D') {
                        if (deque.isEmpty()) {
                            sb.append("error\n");
                            error = true;
                            break;
                        }
                        if (!isReversed) {
                            deque.pollFirst();
                        } else {
                            deque.pollLast();
                        }
                    } else {
                        sb.append("error\n");
                        error = true;
                        break;
                    }
                }

                if (!error) {
                    int size = deque.size();
                    //정답 출력하기
                    sb.append("[");
                    while(!deque.isEmpty()){
                        sb.append(isReversed ? deque.pollLast() : deque.pollFirst());
                        if(!deque.isEmpty()){
                            sb.append(",");
                        }
                    }
                    sb.append("]\n");
                }
            }
            System.out.print(sb);
        }catch (Exception e){
            sb.append("error\n");
        }
    }
}