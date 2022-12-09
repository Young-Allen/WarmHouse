package me.spring.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.commons.lang3.StringUtils;

public class ReflectionUtils {
	 /**
    /**
     * 将属性类型为String，值为Blank的设置为null
     *
     * @param t   t
     * @param <T> T
     */
    public static <T> void stringBlankToNull(T t) {
        if (null == t) {
            return;
        }
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                // 将属性的首字母大写
                String methodName = field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase());
                try {
                    Method methodGet = t.getClass().getMethod("get" + methodName);
                    // 调用getter方法获取属性值
                    String str = (String) methodGet.invoke(t);
                    if (StringUtils.isBlank(str.trim())) {
                        // 如果为String类型的空字符串设置为null
                        field.set(t, null);
                    }
                } catch (Exception e) {
                	throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 将属性类型为String，值为null的设置为 ""
     *
     * @param t   t
     * @param <T> T
     */
    public static <T> void stringNullToEmpty(T t) {
        if (null == t) {
            return;
        }
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                // 将属性的首字母大写
                String methodName = field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase());
                try {
                    Method methodGet = t.getClass().getMethod("get" + methodName);
                    // 调用getter方法获取属性值
                    String str = (String) methodGet.invoke(t);
                    if (StringUtils.isBlank(str)) {
                        // 如果为null的String类型的属性则重新复制为空字符串
                        field.set(t, field.getType().getConstructor(field.getType()).newInstance(""));
                    }
                } catch (Exception e) {
                	throw new RuntimeException(e);
                }
            }
        }
    }

}
