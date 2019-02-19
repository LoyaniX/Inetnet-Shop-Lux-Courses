package com.loyanix.validator;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;

public interface ValidationService {

    void validateAge(int age) throws BusinessException;

    void validateId(ClientService clientService, long id) throws BusinessException;

    void validateId(ProductService productService, long id) throws BusinessException;

    void validateId(OrderService orderService, long id) throws BusinessException;

    void validateEmail(String email) throws BusinessException;

    void validatePhone(String phone) throws BusinessException;

    void isPhoneUniqe (ClientService clientService, String phone) throws BusinessException;
}
