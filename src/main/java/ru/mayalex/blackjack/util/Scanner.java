package ru.mayalex.blackjack.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Scanner implements AutoCloseable {

    @FunctionalInterface
    public interface Validator {

        boolean isValid(int symbol);
    }

    private boolean endOfLine = false;
    private BufferedReader input;
    private Validator validator;
    private boolean isCheckNextLine;
    private boolean hasToken = false;
    private boolean isCheckToken = false;
    private boolean isStartInput = true;
    private int savingSymbol;
    private boolean hasSavingSymbol = false;
    private String token;

    public Scanner(InputStream is) {
        this(is, false);
    }

    public Scanner(InputStream is, boolean isCheckNextLine) {
        this(is, StandardCharsets.UTF_8, isCheckNextLine, symbol -> !Character.isWhitespace(symbol));
    }

    public Scanner(InputStream is, Charset cs, Validator validator) {
        this(is, cs, false, validator);
    }

    public Scanner(InputStream is, Charset cs, boolean isCheckNextLine, Validator validator) {
        input = new BufferedReader(new InputStreamReader(is, cs));
        this.isCheckNextLine = isCheckNextLine;
        this.validator = validator;
    }

    public int nextChar() throws IOException {
        int read = -1;
        if (hasSavingSymbol) {
            read = savingSymbol;
            hasSavingSymbol = false;
        } else {
            read = input.read();
        }
        if (isCheckNextLine && (read == '\n' || read == '\r')) {
            if (read == '\r') {
                read = input.read();
                savingSymbol = read;
                if (read != '\n') {
                    hasSavingSymbol = true;
                }
            }
            endOfLine = true;
        } else {
            endOfLine = false;
        }
        isStartInput = false;
        return read;
    }

    public boolean hasNextLine() {
        if (isStartInput || endOfLine) {
            endOfLine = false;
            return true;
        } else {
            return endOfLine;
        }
    }

    public boolean goToNextLine() {
        boolean check = true;
        try {
            int read;
            while (!hasNextLine() && (read = nextChar()) != -1) {
                if (validator.isValid((char) read)) {
                    check = false;
                }
            }
        } catch (IOException e) {
            System.err.printf("Input failed! %s", e.getMessage());
        }
        return check;
    }

    private boolean hasNext() {
        if (endOfLine) {
            return false;
        }
        StringBuilder string = new StringBuilder();
        int read = -1;
        try {
            while ((read = nextChar()) >= 0 && !endOfLine) {
                char symbol = (char) read;
                if (validator.isValid(symbol)) {
                    string.append(symbol);
                } else {
                    if (string.length() > 0) {
                        token = string.toString();
                        return true;
                    }
                    string.setLength(0);
                }
            }
        } catch (IOException e) {
            System.err.printf("Input failed! %s", e.getMessage());
            return false;
        }
        if (string.length() > 0) {
            token = string.toString();
            return true;
        }
        return false;
    }

    public String next() {
        if (hasNext()) {
            return token;
        }
        return null;
    }

    public boolean hasNextWord() throws IOException {
        isCheckToken = true;
        if (hasNext()) {
            hasToken = true;
            return hasToken;
        }
        hasToken = false;
        return hasToken;
    }

    public String nextWord() throws IOException {
        if ((isCheckToken && hasToken) || (!isCheckToken && hasNextWord())) {
            hasToken = false;
            isCheckToken = false;
            return token;
        }
        return null;
    }

    private int parseInt() {
        // hex prefix length
        final int PREFIX = 2;
        if (token.length() > 1 && token.substring(0, PREFIX).equalsIgnoreCase("0x")) {
            return Integer.parseUnsignedInt(token.substring(PREFIX), 16);
        } else {
            return Integer.parseInt(token);
        }
    }

    private int inputFailed(List<Integer> list, String message) {
        list.clear();
        System.out.println(message);
        return 0;
    }

    public List<Integer> nextIntLine(int maxCount, String message) {
        // :NOTE: Создавать новый список
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        while (count < maxCount) {
            if (hasNextInt()) {
                count++;
                list.add(nextInt());
            } else {
                count = inputFailed(list, message);
            }
            if (count == maxCount) {
                if (goToNextLine()) {
                    return list;
                }
                count = inputFailed(list, message);
            }
        }
        throw new AssertionError("Input failed!");
    }

    public boolean hasNextInt() {
        isCheckToken = true;
        if (hasNext()) {
            try {
                parseInt();
                hasToken = true;
                return hasToken;
            } catch (NumberFormatException e) {
                hasToken = false;
                if (isCheckNextLine && !hasNextLine()) {
                    goToNextLine();
                }
                return hasToken;
            }
        }
        if (isCheckNextLine && !hasNextLine()) {
            goToNextLine();
        }
        hasToken = false;
        return hasToken;
    }

    public int nextInt() {
        if ((isCheckToken && hasToken) || (!isCheckToken && hasNextInt())) {
            hasToken = false;
            isCheckToken = false;
            return parseInt();
        }
        throw new NumberFormatException();
    }

    @Override
    public void close() throws IOException {
        input.close();
    }
}
