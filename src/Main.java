import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        EntryPoint entryPoint = new EntryPoint(sc) ;
        entryPoint.loginOrRegister();
    }
}