package com.ferry.myifood.domain.repository.custom;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

public interface EmailSenderService {
    void enviar(Email email);

    @Getter
    @Builder
    class Email {
        @Singular("to")
        public Set<String> to;
        @NonNull
        public String subject;
        @NonNull
        public String body;
    }
}
