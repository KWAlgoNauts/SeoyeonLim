import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 탑의 개수

        int[] heights = new int[N + 1]; // 탑의 높이 (1번 인덱스부터 시작)
        int[] result = new int[N + 1];  // 결과 저장 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= N; i++) {
            // 스택의 top이 현재 탑보다 작으면 pop (신호를 수신하지 못함)
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                stack.pop();
            }

            // 스택이 비어있지 않다면 현재 탑의 신호를 수신하는 탑이 존재함
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            } else {
                result[i] = 0;
            }

            // 현재 탑을 스택에 push
            stack.push(i);
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}
