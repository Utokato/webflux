package cn.utokato.webflux02.util;

import cn.utokato.webflux02.exception.CheckException;

import java.util.stream.Stream;

/**
 * @author lma
 * @date 2019/09/03
 */
public class CheckUtils {

    private static final String[] INVALID_NAMES = {"admin", "guanliyuan"};

    /**
     * 校验名字，不成功时抛出校验异常
     *
     * @param value
     */
    public static void checkName(String value) {
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value)).findAny().ifPresent(name -> {
            throw new CheckException("name", value);
        });
    }

    public static void checkAge(int age) {
        if (age < 10 || age > 100) {
            throw new CheckException("name", String.valueOf(age));
        }
    }
}
