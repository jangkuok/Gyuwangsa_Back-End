package com.gywangsa.service;


import java.io.IOException;

public interface PortOneService{

    public void refundRequest(String access_token, String merchant_uid, String reason) throws IOException;

    public String getToken(String apiKey, String secretKey) throws IOException;

}
