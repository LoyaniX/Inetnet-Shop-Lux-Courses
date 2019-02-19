package com.loyanix.validator.Impl;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.validator.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {

    private String EMAIL_PATTERN = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private String PHONE_PATTERN = "^((\\+?380)([695][70])([0-9]{7}))$";

    private ClientService clientService;

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age >200) {
            throw new BusinessException("Incorrect age!!!");
        }
    }

    @Override
    public void validateId(ClientService clientService, long id) throws BusinessException {
        if (!clientService
                .findAll()
                .stream()
                .anyMatch(clientDto -> clientDto.getId().equals(id))) throw new BusinessException("Id is absent");
    }

    @Override
    public void validateId(OrderService orderService, long id) throws BusinessException {
        if (!orderService
                .findAll()
                .stream()
                .anyMatch(clientDto -> clientDto.getId().equals(id))) throw new BusinessException("Id is absent");
    }

    @Override
    public void validateId(ProductService productService, long id) throws BusinessException {
        if (!productService
                .findAll()
                .stream()
                .anyMatch(clientDto -> clientDto.getId().equals(id))) throw new BusinessException("Id is absent");
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) throw  new BusinessException("Incorrect email");
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        pattern = Pattern.compile(PHONE_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(phone);
        if (!matcher.matches()) throw  new BusinessException("Incorrect phone");
    }

    @Override
    public void isPhoneUniqe(ClientService clientService, String phone) throws BusinessException {
        if (clientService
                .findAll()
                .stream()
                .anyMatch(clientDto -> clientDto.getPhone().equals(phone))) throw new BusinessException("Phone is not uniqe");
    }
}
