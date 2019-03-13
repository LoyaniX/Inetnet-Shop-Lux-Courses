package com.loyanix.validator.Impl;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.validator.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {

    private static final String EMAIL = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final String PHONE = "^((\\+?380)((67)|(97)|(50))([0-9]{7}))$";

    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PHONE = Pattern.compile(PHONE, Pattern.CASE_INSENSITIVE);
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
    public void validateEmail(String email) throws BusinessException {
    matcher = PATTERN_EMAIL.matcher(email);
        if (!matcher.matches()) throw  new BusinessException("Incorrect email");
    }

    @Override
    public void validatePhone(ClientService clientService, String phone) throws BusinessException {
        matcher = PATTERN_PHONE.matcher(phone);
        boolean isPhoneUnique = clientService.findAll().stream()
                .anyMatch(clientDto -> clientDto.getPhone().equals(phone));
        if (isPhoneUnique) throw new BusinessException("Phone is not unique");
        if (!matcher.matches()) throw  new BusinessException("Incorrect phone");
    }
}
