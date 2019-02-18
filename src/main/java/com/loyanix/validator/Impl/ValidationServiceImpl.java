package com.loyanix.validator.Impl;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.validator.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age >200) {
            throw new BusinessException("Incorrect age!!!");
        }
    }

    @Override
    public void validateId(long id) throws BusinessException {

    }
}
