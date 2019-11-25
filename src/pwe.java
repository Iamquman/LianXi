import java.util.Scanner;
public class pwe {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        //输入一个字符串，加密显示
        String r=sc.nextLine();
        System.out.println(r);
        //secStr(r,0);
        String t=secStr(r,-1);
        System.out.println(t);
        //hello  ifmmp +1
    }
    static String secStr(String s,int n) {   //s表示要加密的字符串，n表示加密到哪个位置
        //拆分字符串的所有字符,
        int len = s.length();
        char[] chs = new char[len];
        //将此字符串的字符复制到目标字符数组中
        s.getChars(0, len, chs, 0);  //String的方法：起始结束位置，保存拆分出来的字符数组的地方，数组中开始的位置

        //每一个字符都输出一下
           for(int i=0;i<len;i++){
               char x=chs[i];
            System.out.println(x);

        //每个字符按ASCII码值进行加密处理
        //判断大小写
        if(Character.isUpperCase(x)) {  //isUpperCase：是大写的
            //大写
            chs[i]=(char)((x-65+26+n)%26+65);  //大写在【65到90】位置，加密后的大写字母还是会落在大写区间内
        }
        if(Character.isLowerCase(x) ){
            //小写
            chs[i]=(char)((x-97+26+n)%26+97);
        }
        //YzAa  +9 =>HiJj
        //A 65 +9=74   [65,90]=>[64+1,89+1]
        }
        //拼接成新字符串
        return new String(chs);

    }
}
