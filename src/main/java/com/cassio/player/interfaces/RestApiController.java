package com.cassio.player.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface RestApiController {
    ObjectMapper jsonSerializer = new ObjectMapper();

    default String serializeObject(Object object) {
        try {
            return jsonSerializer.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            return "";
        }
    }

    default <T> ResponseEntity<T> buildReturn(T entity, HttpHeaders headers, HttpStatus httpStatus) {
        return new ResponseEntity(entity, headers, httpStatus);
    }

    default <T> ResponseEntity<T> buildReturn(T entity, HttpHeaders headers, int httpStatus) {
        return new ResponseEntity(entity, headers, HttpStatus.valueOf(httpStatus));
    }

    default ResponseEntity<String> returnOk() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(this.serializeObject("{}"), headers, HttpStatus.OK);
    }

    default <T> ResponseEntity<T> notImplemented() {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    default <T> ResponseEntity<T> returnOk(T entity) {
        return this.buildReturn(entity, (HttpHeaders)null, HttpStatus.OK);
    }

    default <T> ResponseEntity<T> returnCreated(T entity) {
        return this.returnCreated(entity, (HttpHeaders)null);
    }

    default <T> ResponseEntity<T> returnCreated(T entity, HttpHeaders headers) {
        return this.buildReturn(entity, headers, HttpStatus.CREATED);
    }

    default <T> ResponseEntity<T> returnBadRequest(T entity) {
        return this.buildReturn(entity, (HttpHeaders)null, HttpStatus.BAD_REQUEST);
    }

    default <T> ResponseEntity<T> returnNotFound(T entity) {
        return this.buildReturn(entity, (HttpHeaders)null, HttpStatus.NOT_FOUND);
    }
}