package com.msw.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 公共搜索类型枚举
 *
 */
public enum CommonSearchEnum {

    USER("用户", "user"),
    POST("帖子", "post"),
//    COMPREHENSIVE("综合", "comprehensive"),
//    VIDEO("视频", "video"),
    PICTURE("图片", "picture");

    private final String text;

    private final String value;

    CommonSearchEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return ist<String>
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value value
     * @return FileUploadBizEnum
     */
    public static CommonSearchEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (CommonSearchEnum anEnum : CommonSearchEnum.values()) {
            if (anEnum.value.toLowerCase(Locale.ROOT).equals(value.toLowerCase(Locale.ROOT))) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
