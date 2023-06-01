package com.company.onlineshopping.exception;



import com.company.onlineshopping.enums.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {

    ExceptionType exceptionType;
    ResponseMessages responseMessage;
    NotFoundDto notFoundDto;
    ReplaceMessageDto replaceMessageDto;

    @Override
    public String getMessage() {
        return responseMessage.message();
    }

    public enum ExceptionType {
        DEFAULT, NOT_FOUND, REPLACE_MESSAGE
    }

    public abstract static class BaseType {
        public abstract String asString(String msg);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class NotFoundDto extends BaseType {
        String notFoundKey;
        String target;
        Map<String, Object> fields;

        public static NotFoundDto of(String target, Map<String, Object> fields) {
            return NotFoundDto.builder()
                    .notFoundKey(String.format(ResponseMessages.Error.NOT_FOUND.key(), target.toLowerCase()))
                    .target(target)
                    .fields(fields)
                    .build();
        }

        @Override
        public String asString(String base) {
            return Objects.isNull(fields) || fields.isEmpty() ?
                    "" :
                    String.format(base,
                            target,
                            " by " + fields.entrySet().stream().map((e) -> String.format("%s=%s", e.getKey(), e.getValue()))
                                    .collect(Collectors.joining(","))
                    );
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ReplaceMessageDto extends BaseType {
        String replace;

        public static ReplaceMessageDto of(String replace) {
            return ReplaceMessageDto.builder().replace(replace).build();
        }


        @Override
        public String asString(String msg) {
            return String.format(msg, replace);
        }
    }
}
