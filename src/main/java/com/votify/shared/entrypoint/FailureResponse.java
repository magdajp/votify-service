package com.votify.shared.entrypoint;

import com.votify.shared.result.Failure;
import com.votify.shared.result.NotFoundFailure;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FailureResponse {

    public static ResponseEntity<Object> of(Failure failure) {
        if (failure instanceof NotFoundFailure) {
            return ResponseEntity.status(NOT_FOUND).body(failure.message());
        }
        return ResponseEntity.badRequest().body(failure.message());
    }
}
