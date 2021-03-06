package com.loyanix.validator.Impl;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.validator.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {

    private final String EMAIL_PATTERN = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private final String PHONE_PATTERN = "^((\\+?380)((67)|(97)|(50))([0-9]{7}))$";

    private Pattern pattern;
    private Matcher matcher;

    private final int lowAge = 0;
    private final int highAge = 200;

    @Override
    public void validateAge(int age) throws BusinessException {
        if (lowAge < 0 || age > highAge) {
            throw new BusinessException("Incorrect age!!!");
        }
    }

    @Override
    public void validateId(ClientService clientService, long id) throws BusinessException {
        if (clientService
                .findAll()
                .stream()
                .noneMatch(clientDto -> clientDto.getId().equals(id))) throw new BusinessException("Id is absent");
    }

    @Override
    public void validateId(OrderService orderService, long id) throws BusinessException {
        if (orderService
                .findAll()
                .stream()
                .noneMatch(clientDto -> clientDto.getId().equals(id))) throw new BusinessException("Id is absent");
    }

    @Override
    public void validateId(ProductService productService, long id) throws BusinessException {
        if (productService
                .findAll()
                .stream()
                .noneMatch(clientDto -> clientDto.getId().equals(id))) throw new BusinessException("Id is absent");
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) throw  new BusinessException("Incorrect email");
    }

    @Override
    public void validatePhone(ClientService clientService, String phone) throws BusinessException {
        pattern = Pattern.compile(PHONE_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(phone);
        boolean isPhoneUnique = clientService.findAll().stream()
                .anyMatch(clientDto -> clientDto.getPhone().equals(phone));
        if (isPhoneUnique) throw new BusinessException("Phone is not unique");
        if (!matcher.matches()) throw  new BusinessException("Incorrect phone");
    }
}
