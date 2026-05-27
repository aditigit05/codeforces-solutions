import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String[] parts = br.readLine().split(" ");
            int n = Integer.parseInt(parts[0]);
            long k = Long.parseLong(parts[1]);
            int x = Integer.parseInt(parts[2]) - 1;

            long[] h = new long[n];
            parts = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                h[i] = Long.parseLong(parts[i]);
            }

            int[] d = new int[n - 1];
            parts = br.readLine().split(" ");
            for (int i = 0; i < n - 1; i++) {
                d[i] = Integer.parseInt(parts[i]);
            }

            long[] reach = new long[n];
            Arrays.fill(reach, (long)1e18);

            reach[x] = 1;

            int L = x, R = x;

            boolean changed = true;

            while (changed) {
                changed = false;

                // expand left
                while (L > 0) {
                    long newTime = Math.max(reach[L], d[L - 1]);
                    if (newTime > k) break;

                    if (newTime < reach[L - 1]) {
                        reach[L - 1] = newTime;
                        L--;
                        changed = true;
                    } else {
                        break;
                    }
                }

                // expand right
                while (R < n - 1) {
                    long newTime = Math.max(reach[R], d[R]);
                    if (newTime > k) break;

                    if (newTime < reach[R + 1]) {
                        reach[R + 1] = newTime;
                        R++;
                        changed = true;
                    } else {
                        break;
                    }
                }
            }

            long ans = 0;
            for (int i = 0; i < n; i++) {
                if (reach[i] <= k) {
                    ans += (k - reach[i] + 1) * h[i];
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
