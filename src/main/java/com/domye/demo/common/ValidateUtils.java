package com.domye.demo.common;

import javax.validation.constraints.NotNull;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 参数校验工具类
 */
public class ValidateUtils {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 校验对象
     * @param obj 待校验对象
     * @param <T> 对象类型
     * @return 校验结果，如果校验通过则返回null，否则返回错误信息
     */
    public static <T> String validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        if (constraintViolations.isEmpty()) {
            return null;
        }
        return constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
    }

    /**
     * 校验对象，如果校验失败则抛出异常
     * @param obj 待校验对象
     * @param <T> 对象类型
     */
    public static <T> void validateAndThrow(T obj) {
        String errorMessage = validate(obj);
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}