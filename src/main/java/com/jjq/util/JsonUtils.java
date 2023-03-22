package com.jjq.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jjq.util.core.TemporalFormatPattern;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author jjq
 * @version 1.0
 * @date 2023/3/12
 * @desc
 */
public class JsonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static JavaTimeModule javaTimeModule = new JavaTimeModule();

    static {
        //如果序列化对象的属性是null时，结果将忽略该属性，否则该属性将序列化成null
        //注意如果序列化null时，一定是输出null，即object2json(null) = "null"
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //遇到无法序列化的对象，不抛出异常，默认是抛出异常，例如如果序列化一个HttpServletResponse对象，将跑出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化时，遇到不认识的字段将跳过
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //设置java.lang.Date的序列化和反序列化的格式
        objectMapper.setDateFormat(new SimpleDateFormat(TemporalFormatPattern.P1));
        //设置 java.time.LocalDateTime 的序列化器和反序列化器
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(TemporalFormatPattern.P1)));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(TemporalFormatPattern.P1)));
        //设置 java.time.LocalDate 的序列化器和反序列化器
        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(TemporalFormatPattern.P2)));
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(TemporalFormatPattern.P2)));
        //注册到objectMapper中
        objectMapper.registerModule(javaTimeModule);
    }

    @SneakyThrows
    public static String object2json(Object obj) {
        return (obj instanceof String) ? (String) obj : objectMapper.writeValueAsString(obj);
    }

    public static String object2jsonPretty(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return  (obj instanceof String) ? (String) obj :
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T json2object(String json, Class<T> clazz) {
        if (json == null || "".equals(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {

            return null;
        }
    }


    public static <T> List<T> json2list(String json, Class<T> clazz) {
        if (json == null || "".equals(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>(){});
        } catch (JsonProcessingException e) {

            return null;
        }
    }


}
