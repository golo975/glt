import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Java8LocalDateTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * @since 2017年9月20日
     */
    @Test
    public void localDate_now_Test() {


        // 注意区别：LocalDate的返回值就是实际的返回值，Date中获取的月份=实际值-1。
        LocalDate now = LocalDate.now();
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.format(formatter));

        // 上海
        LocalDate shanghaiNow = LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
        System.out.println(shanghaiNow.getYear());
        System.out.println(shanghaiNow.getMonthValue());
        System.out.println(shanghaiNow.getDayOfMonth());
        System.out.println(shanghaiNow.format(formatter));

        // Los_Angele
        LocalDate laNow = LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("PST")));
        System.out.println(laNow.format(formatter));


    }

    @Test
    public void localDate_of_Test() {
        LocalDate now1 = LocalDate.now();
        LocalDate now2 = LocalDate.of(now1.getYear(), now1.getMonth(), now1.getDayOfMonth());
        LocalDate now3 = LocalDate.of(now1.getYear(), now1.getMonthValue(), now1.getDayOfMonth());
        LocalDate now4 = LocalDate.ofYearDay(now1.getYear(), now1.getDayOfYear());
        // EpochDay 就是从 1970-01-01 算起，距今的天数
//        LocalDate now5 = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000));
        LocalDate now5 = LocalDate.ofEpochDay(System.currentTimeMillis() / DateUtils.MILLIS_PER_DAY);
        System.out.println(now1.equals(now2));
        System.out.println(now1.equals(now3));
        System.out.println(now1.equals(now4));
        System.out.println(now1.equals(now5));
    }




}
