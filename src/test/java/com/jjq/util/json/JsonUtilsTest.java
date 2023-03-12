package com.jjq.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

/**
 * @author jiangjunqing
 * @version v0.0.1
 * @date 2023/3/12
 * @desc
 */
@Slf4j

public class JsonUtilsTest {

    private static ObjectMapper objectMapper = null;

    static  {
        Field objectMapper1 = null;
        try {
            objectMapper1 = JsonUtils.class.getDeclaredField("objectMapper");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        objectMapper1.setAccessible(true);
        try {
            objectMapper = (ObjectMapper)objectMapper1.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testEmptyObject() {
        EmptyObject emptyObject = new EmptyObject();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        Exception exception1 = null;
        try {
            String s = JsonUtils.object2json(emptyObject);
        } catch (Exception e) {
            log.error("1, e", e);
            exception1 = e;
        }
        Assertions.assertNotNull(exception1, "1=success");

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        Exception exception2 = null;
        try {
            String s = JsonUtils.object2json(emptyObject);
        } catch (Exception e) {
            exception2 = e;
        }
        Assertions.assertNull(exception2, "2=success");
    }

    @Test
    public void testSerNull() {

        EmptyObject emptyObject = new EmptyObject();
        //default
        //objectMapper.setSerializationInclusion(null);
//        String s1 = JsonUtils.object2json(null);
//        Assertions.assertEquals("null", s1, "序列化null不是输出null串");
//        String s2 = JsonUtils.object2json(emptyObject);
//        Assertions.assertTrue(s2.contains("name"), "序列化空属性时将输出该属性");


        //序列化时忽略null属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //String s3 = JsonUtils.object2json(null);
        //Assertions.assertEquals("null", s3, "序列化null不是输出null串");
        String s4 = JsonUtils.object2json(emptyObject);
        Assertions.assertTrue(!s4.contains("name"), "设置忽略空属性后，序列化空属性时将忽略该属性");
    }




}
