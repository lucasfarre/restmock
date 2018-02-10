package com.lucasfarre.restmock.constants;

public final class ErrorMessages {

    public static final String CAN_NOT_INSTANTIATE_UTILITY_CLASS = "Can not instantiate utility class.";

    private ErrorMessages() {
        throw new AssertionError(CAN_NOT_INSTANTIATE_UTILITY_CLASS);
    }
}
