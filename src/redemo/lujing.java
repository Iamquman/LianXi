//20191005
package redemo;

import java.io.File;

public class lujing {
    public static void main(String[] args) {
        File f=new File("..\\OOPDemo");
        if(f.exists()){
            System.out.println("文件存在");
        }else{
            System.out.println("文件不存在");
        }
        System.out.println(f.getAbsolutePath());  //返回此抽象路径名的绝对路径名字符串


    }
}
