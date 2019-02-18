package com.loyanix.validator;

import com.loyanix.exeptions.BusinessException;

public interface ValidationService {

    void validateAge (int age) throws BusinessException;

    void validateId(long id) throws BusinessException;
}
