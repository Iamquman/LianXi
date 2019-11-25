package user;

import java.util.Scanner;

//1.增加学员姓名，2.在某个区间内查找学员姓名，如果找到则返回
public class Student {
    String[] names=new String[30];

    //增加姓名,name为需要添加进数组的姓名
    public void addname(String name){
        boolean flag=false;
        for(int i=0;i<names.length;i++){
            if(names[i]==null){
                flag=true;
                names[i]=name;
                break;
            }
            if(!flag) {
                System.out.println("数组已满，无法添加");
            }
        }

    }

    //查找姓名
    public boolean loopUp(int start,int end,String name){
        boolean flag=false;   //标识是否查找到学员
        for(int i=start-1;i<end;i++){
            if(names[i].equals(name)){
                flag=true;
                break;
            }
        }
       return flag;
    }

    public void showNames(){
        System.out.println("学员列表：");
        for(int i=0;i<names.length;i++){
            if(names[i]!=null){
                System.out.println(names[i]+"\t");
            }
        }
    }

    public static void main(String[] arg){
        Student st=new Student();
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<5;i++){
            System.out.print("请输入学员姓名：");
            st.addname(sc.next());
        }
        st.showNames();

    }
}
