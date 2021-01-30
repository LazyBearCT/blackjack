package ru.mayalex.blackjack.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Scanner implements AutoCloseable {

    private BufferedReader br;
    private StringTokenizer st;

    public Scanner (InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = br.readLine();
            if (s == null) {
                return null;
            }
            st = new StringTokenizer(s);
        }
        return st.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    public char nextChar() throws IOException {
        return (char) (br.read());
    }

    public String nextLine() throws IOException {
        return br.readLine();
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}
