import java.io.*;

public class M2447 {
    static int N;
    static StringBuilder sb = new StringBuilder();
    static char[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = new char[N][N];

        draw(0, 0, N, false);

        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.print(sb);
    }

    private static void draw(int x, int y, int size, boolean isBlank) {
        if (isBlank) {
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    answer[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            answer[x][y] = '*';
            return;
        }

        int newSize = size / 3;
        int count = 0;
        for (int i = x; i < x + size; i += newSize) {
            for (int j = y; j < y + size; j += newSize) {
                count++;
                // 가운데면 공백
                if (count == 5) {
                    draw(i, j, newSize, true);
                } else {
                    draw(i, j, newSize, false);
                }
            }
        }
    }
}
