//时间日期
//import java.sql.SQLOutput;
import java.time.Instant;   //表示确定的一个时刻
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Time {
    public static void main(String[] args) {
        Instant ti=Instant.now();
        System.out.println((ti.toString()));
        LocalDateTime lt=LocalDateTime.now();
        System.out.println(lt.toString());
        LocalDateTime zt=LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zt.toString());
        System.out.println(ZoneId.getAvailableZoneIds());
        ZonedDateTime zdt=ZonedDateTime.now(ZoneId.of("Europe/London"));
        System.out.println(zdt.toString());
        System.out.println(zdt.toLocalDateTime().toString());

       ZonedDateTime zdt1= zdt.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println(zdt1.toString());
        int y=zdt1.getYear();
        int m=zdt1.getMonthValue();
        int d=zdt1.getDayOfMonth();
        int h=zdt1.getHour();
        int mm=zdt1.getMinute();
        int s=zdt1.getSecond();
        int n=zdt1.getNano();
        System.out.printf("%4d-%02d-%02d %02d:%02d:%02d.%d\n",y,m,d,h,mm,s,n);
        System.out.println(zdt1.isEqual(zdt));
    }
}
