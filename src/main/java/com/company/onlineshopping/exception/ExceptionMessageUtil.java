package com.company.onlineshopping.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessageUtil {

    private BaseException ex;
    private static final Map<BaseException.ExceptionType,
            Function<BaseException, BaseException.BaseType>> asStringByType;


    static {
        asStringByType = new HashMap<>();
        asStringByType.put(BaseException.ExceptionType.DEFAULT, (b) -> new BaseException.BaseType() {
            @Override
            public String asString(String msg) {
                return msg;
            }
        });
        asStringByType.put(BaseException.ExceptionType.NOT_FOUND, BaseException::getNotFoundDto);
        asStringByType.put(BaseException.ExceptionType.REPLACE_MESSAGE, BaseException::getReplaceMessageDto);
    }

    public String asString() {
        return asStringByType.getOrDefault(
                ex.getExceptionType(),
                asStringByType.get(BaseException.ExceptionType.DEFAULT)
        ).apply(ex).asString(ex.getResponseMessage().message());
    }

    public static ExceptionMessageUtil of(BaseException ex) {
        return new ExceptionMessageUtil(ex);
    }
}
