package ar.edu.unrc.dc.views;

import java.util.Scanner;

public class ConsoleIO implements InputOutput{
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String string) {
        System.out.println(string);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public int readInt() {
        return scanner.nextInt();
    }
}
