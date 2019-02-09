package com.task.holiday.tools;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public abstract class ResponseFactory {

    public static <T> ResponseEntity<T> optionalOk(Optional<T> instance) {
        if(instance.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(instance.get());
        return badRequest();
    }

    public static <T> ResponseEntity<T> ok(T instance) {
        return ResponseEntity.status(HttpStatus.OK).body(instance);
    }
    public static ResponseEntity badRequest(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
