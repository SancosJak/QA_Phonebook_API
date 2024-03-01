package com.phonebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class ErrorDTO {
    private int status;
    private Object message;
    private String error;
}
