public class MaoPaoPXTest {
    public static void main(String[] args) {
        /*for(int i=1;i<=9;i++){
            for(int j=1;j<=i;j++){
                System.out.printf("%d*%d=%2d\t",j,i,i*j);
            }
            System.out.println();
        }*/
   /*     for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print("*");
            }
            System.out.println();
        }*/
        int[] arr={34,25,19,20,55};
        for(int i=0;i<arr.length-1;i++){     //五个数比4次，因此arr.length-1
            for(int j=0;j<arr.length-1-i;j++){
            if(arr[j]>arr[j+1]) {
                int t=arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=t;
                }
            }
        }
        for(int v:arr){
            System.out.print(v+"\t");

        }

    }
}
