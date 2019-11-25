public class DaoSanJiao {
    public static void main(String[] args) {
        for(int i=5;i>=0;i--){
            for(int z=0;z<=i;z++) {
                System.out.print(" ");
            }
            for (int j=i-2;j>=1;j--){
                System.out.print("*");
            }
            System.out.println();
        }

    }
}
