package cn.itcast.ssm.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//自定义转换器
@Component
public class StringToDateConverter implements Converter<String,Date> {

    public Date convert(String source) {

        if (source == null) {
            throw new RuntimeException("请输入数据");
        }

        if (source.contains("-") && !source.contains("/")) {

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            try {
                Date date = dateFormat.parse(source);

                return date;
            } catch (ParseException e) {
                throw new RuntimeException("输入数据格式有误");
            }
        }

        if (source.contains("/") && !source.contains("-")) {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            try {
                Date date = dateFormat.parse(source);

                return date;
            } catch (ParseException e) {
                throw new RuntimeException("输入数据格式有误");
            }


        }


        throw new RuntimeException("输入数据格式有误");
    }
}
