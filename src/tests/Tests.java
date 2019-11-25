package tests;
//20191009
public class Tests {

    static boolean isRunnian(){
        return false;

    }

    static boolean strIn(String a, String b) {
        //编写代码，解决：字符串a是否完全包含在字符串b中、
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        int alen=aa.length;
        int blen=bb.length;
        int i=0, j = 0;
        boolean flag=false;
        do{
            System.out.printf("a[%d]：%c与b[%d]:%c",i,aa[i],j,bb[j]);
            if(aa[i]==bb[j]){
                flag=true;
                System.out.println("相等，同时后移");
               i++;
               if(i==alen) return true;
               j++;
            }else{
                System.out.println("不相等，继续找");
                i=0;
                if(!flag) j++;  //j+= flag?0:1;
                flag=false;
            }
            if(flag){
                System.out.println("a前移，b前移");
            }else{
                System.out.println("a回到0，b不动");
            }
        }while(i<alen&&j<blen);
        return false;
    }

    public static void main(String[] args) {
        if(strIn("def","redefine")){
            System.out.println("找到了");
        }else {
            System.out.println("没找到");
        }
        String s1="def";
        String s2="redefine";
        System.out.println(s2.indexOf(s1));
        System.out.println(s2.indexOf(s1));
        System.out.println(s2.substring(1));
        System.out.println(s2.substring(1,6));

    }
}
