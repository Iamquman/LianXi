package redemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//递归算法
public class RecursionDemo {
    static int f(int n){
        if(n<3){
            return 1;
        }else{
            return f(n-1)+f(n-2);
        }
    }

    static List<String> getFiles(File dir){
        if(!dir.isDirectory())return null;
        List<String> fileNames=new ArrayList<>();
        File[] files=dir.listFiles();
        for(File f:files){
            if(f.isFile()) {
                fileNames.add(f.getName());

            }else{
                List<String> fs=getFiles(f);
                fileNames.addAll(fs);
            }
        }
            return fileNames;
    }

    public static void main(String[] args) {
        /*System.out.println(f(5));
        System.out.println(f(7));*/
        File f=new File("D:\\BIGDATA");
        List<String> files=getFiles(f);
        for(String fn:files){
            System.out.println(fn);
        }
    }
}
