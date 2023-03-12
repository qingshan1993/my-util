package com.jjq.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;

/**
 * @author jiangjunqing
 * @version v0.0.1
 * @date 2023/3/13
 * @desc
 */
public class JacksonIssueTest {


    public static void main(String[] args) throws JsonProcessingException {
        CommonObject commonObject = new CommonObject();
        commonObject.setAge("18");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //default
        objectMapper.setSerializationInclusion(null);
        String s2 = objectMapper.writeValueAsString(commonObject);
        System.out.println("s2: " + s2);
        //s2 = {"name":null,"age":"18"}
        Assertions.assertTrue(s2.contains("name"));

        //ignore null field
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String s4 = objectMapper.writeValueAsString(commonObject);
        System.out.println("s4: " + s4);
        // s4 = {"name":null,"age":"18"}
        Assertions.assertTrue(!s4.contains("name"));

    }

    public static class CommonObject{
        private String name;
        private String age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
