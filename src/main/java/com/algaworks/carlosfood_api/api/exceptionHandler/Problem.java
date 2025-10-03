package com.algaworks.carlosfood_api.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessager;
    private LocalDateTime timestamp;
    private List<Field> fields;


    @Getter
    @Builder
    public static class Field {
        private String nome;
        private String userMessager;
    }

}
