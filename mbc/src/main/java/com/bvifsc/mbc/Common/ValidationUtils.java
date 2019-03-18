package com.bvifsc.mbc.Common;

/**
 * Created by Ramesh on 11-12-2018.
 */
public class ValidationUtils {

    private static final String userIDPattern = "([a-zA-Z0-9@#$_.'+-]+){6,60}$";
    private static final String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9`~!@#$%\\^&*\\(\\)\\-_=+\\[\\]{}\\\\|;:'\",.<>\\/?]){8,20}$";
    private static final String namePattern = "^[A-Za-z0-9 ]+$";
    private static final String emailPattern = "^[a-zA-Z0-9](\\.?\\_?\\-?[a-zA-Z0-9]){0,}@[a-zA-Z0-9-_]+\\.([a-zA-Z0-9-_]{1,}\\.){0,}[a-zA-Z]{2,}$";
    private static final String cityNamePattern = "^[A-Za-z]+$";
    private static final String phoneNumberPattern = "^([0-9]){10}$";
    private static final String zipCodePattern = "^[A-Za-z0-9]{4,9}$";
    private static final String securityAnswerPattern = "^[A-Za-z0-9. ]{3,40}";
    private static final String professionalDesignationPattern= "Mr\\.|MR\\.|Mrs\\.|Dr\\.|Ms\\.|MS\\.";



    public static boolean isValidUserName(String userName) {
        return !CommonUtils.checkNullOrEmptyString(userName)  && userName.matches(userIDPattern);
    }

    public static boolean isValidPassword(String password)
    {
        return !CommonUtils.checkNullOrEmptyString(password)  && password.matches(passwordPattern);

    }

    public static boolean isValidName(String name)
    {
        return  !CommonUtils.checkNullOrEmptyString(name)  && name.matches(namePattern);

    }
    public static boolean isValidEmail(String email)
    {
        return  !CommonUtils.checkNullOrEmptyString(email)  && email.matches(emailPattern);

    }

    public static boolean isValidCityName(String cityName)
    {
        return  !CommonUtils.checkNullOrEmptyString(cityName)  && cityName.matches(cityNamePattern);

    }
    public static boolean isValidPhoneNumber(String phoneNumber)
    {
        return  !CommonUtils.checkNullOrEmptyString(phoneNumber)  && phoneNumber.matches(phoneNumberPattern);
    }

    public static boolean isValidZipCode(String zipCode)
    {
        return  !CommonUtils.checkNullOrEmptyString(zipCode)  && zipCode.matches(zipCodePattern);

    }

    public static boolean isValidSecurityAnswer(String securityAnswer)
    {
        return  !CommonUtils.checkNullOrEmptyString(securityAnswer)  && securityAnswer.matches(securityAnswerPattern);

    }

    public static boolean isValidProfessionalDesignation(String professionalDesignation)
    {

        return !CommonUtils.checkNullOrEmptyString(professionalDesignation) && professionalDesignation.matches(professionalDesignationPattern);
    }




}
