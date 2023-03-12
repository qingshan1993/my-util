package com.jjq.util.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;

/**
 * @author jiangjunqing
 * @version v0.0.1
 * @date 2023/3/12
 * @desc
 */
@Getter
@Setter
public class CannotSerObject {

    private String name;


    private ByteArrayOutputStream bos;


    private ByteArrayOutputStream bos1;
}
