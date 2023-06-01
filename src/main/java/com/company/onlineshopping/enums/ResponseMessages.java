package com.company.onlineshopping.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

public interface ResponseMessages {

    String key();

    String message();

    HttpStatus status();

    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    enum Success implements ResponseMessages {
        SUCCESS("success", "Success", HttpStatus.OK);

        String key;
        String message;
        HttpStatus status;

        @Override
        public String key() {
            return key;
        }

        @Override
        public String message() {
            return message;
        }

        @Override
        public HttpStatus status() {
            return status;
        }
    }

    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    enum Error implements ResponseMessages {
        NOT_FOUND("not_found_%s", "%s can't find%s", HttpStatus.NOT_FOUND),
        UNEXPECTED("unexpected", "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR),
        VALIDATION_ERROR("validation_error", "Validation error", HttpStatus.BAD_REQUEST);

        String key;
        String message;
        HttpStatus status;

        @Override
        public String key() {
            return key;
        }

        @Override
        public String message() {
            return message;
        }

        @Override
        public HttpStatus status() {
            return status;
        }
    }
}
