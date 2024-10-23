package com.samhcoco.ip.bookingapp.core.service;

import java.util.Map;

public interface ValidatorService<T> {

    /**
     * Validates given {@link T}.
     * @param object {@link T} instance to validate.
     * @return Empty if validation passed or failure reasons if failed.
     */
    Map<String, Object> validate(T object);

}
