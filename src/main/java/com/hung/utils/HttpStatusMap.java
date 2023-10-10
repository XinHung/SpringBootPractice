package com.hung.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HttpStatusMap {
    private static Map<HttpStatus, Integer> map = new HashMap<>();

    static {
        map.put(HttpStatus.OK, 200);
        map.put(HttpStatus.BAD_REQUEST, 400);
        map.put(HttpStatus.UNAUTHORIZED, 401);
        map.put(HttpStatus.FORBIDDEN, 403);
        map.put(HttpStatus.NOT_FOUND, 404);
        map.put(HttpStatus.PRECONDITION_FAILED, 412);
        map.put(HttpStatus.INTERNAL_SERVER_ERROR, 500);
    }

    public static Integer get(HttpStatus status) {
        return map.get(status);
    }
}
