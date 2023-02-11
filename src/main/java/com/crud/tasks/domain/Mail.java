package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
}
