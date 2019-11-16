package my;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.validator.constraints.EAN;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        String s = DateFormatUtils.format(calendar.getTime(), "yyyy年MM月dd日");
        System.out.println(s);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        s = DateFormatUtils.format(calendar.getTime(), "yyyy年MM月dd日");
        System.out.println(s);
    }
}
