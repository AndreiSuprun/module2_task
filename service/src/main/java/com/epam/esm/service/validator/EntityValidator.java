package com.epam.esm.service.validator;

import com.epam.esm.service.exception.ErrorCode;
import com.epam.esm.service.exception.ProjectException;

public abstract class EntityValidator<T> {

    public abstract void validate(T object);

    protected <V> void validateField(Validator<V> validator,
                                     V field, ErrorCode errorCode, String... params) {
        if (!validator.isValid(field)) {
            throw new ProjectException(errorCode, params);
            }
        }
    }


