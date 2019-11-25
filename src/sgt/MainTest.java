package sgt;

public class MainTest {
    public static void main(String[] args) {
        Printer p=Printer.getPrinter();
        Printer q=Printer.getPrinter();
        System.out.println(p==q);
    }
}
