import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ShuZu {
    public static void main(String[] args) {
       /* int[] a=new int[30];
        for(int i=0;i<30;i++){
            a[i]=i;
        }
        System.out.println(a);
        Random r=new Random();
        Random ra=new Random();
        System.out.println("r:"+r.nextInt()+"\tra:"+ra.nextInt());*/
       String s="hello,my name is quman!ok";
        //System.out.println(s.indexOf(97));
        String[] m=s.split(" ");
        for(String mm:m){
            System.out.println(mm);
        }

    }
}
