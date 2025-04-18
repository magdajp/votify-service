package com.votify.shared.entrypoint;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse {
    public static ResponseEntity<Object> created(Object body) {
        return ResponseEntity.status(CREATED).body(body);
    }
}
