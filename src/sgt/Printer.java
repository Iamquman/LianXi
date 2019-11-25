package sgt;
//20190924
public class Printer {
    private Printer(){}
    final private static Printer me=new Printer();
    public static Printer getPrinter(){
        return me;
    }
}
