package io.zgc.design.patterns;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author codingxcong
 * @date 2023-09-10
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Person p1 = new Person("1", 10);
        Person p2 = new Person("2", 12);
        Person p3 = new Person("3", 13);
        Person p4 = new Person("1", 14);
        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        Map<String, Person> oldMap = personList.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println(oldMap);


        Integer integer = Integer.valueOf("1.1");
        System.out.println(integer);

        // 初始化dateRangeMap
        Map<String, List<DateRange>> dateRangeMap = new HashMap<>();

        // 添加一些数据到dateRangeMap
        List<DateRange> rangeList1 = new ArrayList<>();
        rangeList1.add(new DateRange(parseDate("2023-03-01"), parseDate("2023-03-10")));
        rangeList1.add(new DateRange(parseDate("2023-07-05"), parseDate("2023-07-15")));
        rangeList1.add(new DateRange(parseDate("2023-08-10"), parseDate("2023-08-20")));
        dateRangeMap.put("Key1", rangeList1);

        List<DateRange> rangeList2 = new ArrayList<>();
        rangeList2.add(new DateRange(parseDate("2023-04-01"), parseDate("2023-04-10")));
        rangeList2.add(new DateRange(parseDate("2023-05-05"), parseDate("2023-05-15")));
        dateRangeMap.put("Key2", rangeList2);

        List<DateRange> rangeList3 = new ArrayList<>();
        rangeList3.add(new DateRange(parseDate("2023-01-01"), parseDate("2023-01-10")));
        rangeList3.add(new DateRange(parseDate("2023-05-07"), parseDate("2023-05-15")));
        dateRangeMap.put("Key3", rangeList3);

        // 遍历dateRangeMap，并按照List<DateRange>中的第一个元素排序
        dateRangeMap.forEach((key, value) -> {
            List<DateRange> sortedList = value.stream()
                    .sorted(Comparator.comparing(DateRange::getStartDate))
                    .collect(Collectors.toList());

            // 输出排序后的List
            System.out.println("Key: " + key);
            sortedList.forEach(dateRange -> {
                System.out.println(dateRange.getStartDate() + " - " + dateRange.getEndDate());
            });
        });
    }

    // 辅助方法：将字符串解析为日期
    private static Date parseDate(String dateString) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }
}

@Data
class Person {
    private String id;
    private Integer age;

    public Person(String id, Integer age) {
        this.id = id;
        this.age = age;
    }
}

class DateRange {
    private Date startDate;
    private Date endDate;

    public DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
