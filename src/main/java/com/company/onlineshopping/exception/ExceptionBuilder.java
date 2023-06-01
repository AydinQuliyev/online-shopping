package com.company.onlineshopping.exception;



import com.company.onlineshopping.enums.ResponseMessages;

import java.util.Optional;

import static com.company.onlineshopping.enums.ResponseMessages.Error.UNEXPECTED;


public class ExceptionBuilder {

    public static BaseException of(ResponseMessages message) {
        return BaseException.builder().exceptionType(BaseException.ExceptionType.DEFAULT).responseMessage(message).build();
    }

    public static BaseException of(Exception ex) {
        return ofReplace(UNEXPECTED, ex.getMessage());
    }

    public static BaseException unexpected() {
        return of(UNEXPECTED);
    }

    public static BaseException ofReplace(ResponseMessages message, String replace) {
        return BaseException.builder().exceptionType(BaseException.ExceptionType.REPLACE_MESSAGE)
                .responseMessage(message).replaceMessageDto(BaseException.ReplaceMessageDto.of(replace)).build();
    }

    public static BaseException notFound(String target, ExceptionParam builder) {
        return BaseException.builder().exceptionType(BaseException.ExceptionType.NOT_FOUND)
                .responseMessage(ResponseMessages.Error.NOT_FOUND)
                .notFoundDto(BaseException.NotFoundDto.of(
                        target, Optional.ofNullable(builder).map(ExceptionParam::build).orElse(null))
                ).build();
    }

    public static BaseException notFound(String target, String field, Object value) {
        return notFound(target, ExceptionParam.collect().param(field, value));
    }

    public static BaseException notFound(Class<?> clazz, ExceptionParam builder) {
        return notFound(clazz.getSimpleName(), builder);
    }

    public static BaseException notFound(Class<?> clazz, String field, Object value) {
        return notFound(clazz.getSimpleName(), ExceptionParam.collect().param(field, value));
    }

    public static BaseException notFound(Class<?> clazz) {
        return notFound(clazz, null);
    }

    public static BaseException notFound(String target) {
        return notFound(target, null);
    }

}
