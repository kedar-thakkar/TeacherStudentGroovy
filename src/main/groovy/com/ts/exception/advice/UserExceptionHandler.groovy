package com.ts.exception.advice

import com.ts.constant.AppConstants
import com.ts.dto.ErrorResponseDTO
import com.ts.exception.InvalidCredException
import com.ts.exception.UserNotVerifiedException
import org.springframework.http.HttpStatus

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

class UserExceptionHandler {

    @ExceptionHandler(UserNotVerifiedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponseDTO handler(UserNotVerifiedException exception) {
        return new ErrorResponseDTO(String.valueOf(HttpStatus.BAD_REQUEST), exception.getMessage(), new ArrayList<>());

    }

    @ExceptionHandler(InvalidCredException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponseDTO handler(InvalidCredException exception) {
        return new ErrorResponseDTO(String.valueOf(HttpStatus.BAD_REQUEST), exception.getMessage(), new ArrayList<>());
    }
}
