package ru.s100p.petsmongo.exception;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**КЛАСС ДЛЯ КАСТОМНЫХ УВЕДОМЛНИЙ О ВНУТРЕННИХ ОШИБКАХ И ЗАБОРА ERROR MESSAGE ИЗ КОНСОЛИ (СМ https://for-each.dev/lessons/b/-global-error-handler-in-a-spring-rest-api)  **/

@Data
@NoArgsConstructor
public class ErrorResponse {

        private HttpStatus status;
        private String message;
       // private List<String> errors;

        public ErrorResponse(HttpStatus status, String message, List<String> errors) {
                super();
                this.status = status;
                this.message = message;
             //   this.errors = errors;
        }

        public ErrorResponse(HttpStatus status, String message/*, String error*/) {
                super();
                this.status = status;
                this.message = message;
             //   errors = Arrays.asList(error);


        }}
