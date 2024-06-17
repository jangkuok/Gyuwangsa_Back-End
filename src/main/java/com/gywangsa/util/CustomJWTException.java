package com.gywangsa.util;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

//사용자 정의 예외 처리
public class CustomJWTException extends RuntimeException {

    public CustomJWTException(String msg){
        super(msg);
    }
}
