package com.company.onlineshopping.response;


import com.company.onlineshopping.enums.ResponseMessages;
import com.company.onlineshopping.exception.BaseException;
import com.company.onlineshopping.exception.ExceptionMessageUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 34829384792L;

    public enum MessageType {
        SUCCESS, ERROR
    }

    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Message {
        String key;
        String message;
        MessageType type;

        public static <E extends ResponseMessages> Message of(E e) {
            return Message.builder()
                    .key(e.key())
                    .message(e.message())
                    .type(e instanceof ResponseMessages.Success ? MessageType.SUCCESS : MessageType.ERROR)
                    .build();
        }

        public static Message error(BaseException e) {
            boolean isNotFound = e.getExceptionType().equals(BaseException.ExceptionType.NOT_FOUND);
            return Message.builder()
                    .key(isNotFound ? e.getNotFoundDto().getNotFoundKey() : e.getResponseMessage().key())
                    .message(ExceptionMessageUtil.of(e).asString())
                    .type(MessageType.ERROR)
                    .build();
        }

        public static Message error(Exception e) {
            return Message.builder()
                    .key(ResponseMessages.Error.UNEXPECTED.key())
                    .message(ResponseMessages.Error.UNEXPECTED.message())
                    .type(MessageType.ERROR)
                    .build();
        }
    }

    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ValidationMessage {
        String field;
        String message;

        public static ValidationMessage validation(String field, String message) {
            return ValidationMessage.builder().field(field).message(message).build();
        }

        public static ValidationMessage validation(FieldError fieldError) {
            return validation(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    HttpStatus status;
    Message message;
    List<ValidationMessage> validationMessages;
    T data;

    public static <T, E extends ResponseMessages> BaseResponse<T> of(T data, E e, List<ValidationMessage> validationMessages) {
        return BaseResponse.<T>builder()
                .status(e.status())
                .message(Message.of(e))
                .validationMessages(validationMessages)
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> success(T data) {
        return of(data, ResponseMessages.Success.SUCCESS, null);
    }

    public static <T> BaseResponse<T> success() {
        return of(null, ResponseMessages.Success.SUCCESS, null);
    }

    public static <T> BaseResponse<T> validation(List<ValidationMessage> validationMessages) {
        return of(null, ResponseMessages.Error.VALIDATION_ERROR, validationMessages);
    }

    public static BaseResponse<?> error(BaseException ex) {
        return BaseResponse.builder().status(ex.getResponseMessage().status()).message(Message.error(ex)).build();
    }

    public static BaseResponse<?> error(Exception ex) {
        return BaseResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(Message.error(ex)).build();
    }
}
