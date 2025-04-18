package com.votify.shared;

public record Email(String value) {

    @Override
    public String toString() {
        return value;
    }
}
