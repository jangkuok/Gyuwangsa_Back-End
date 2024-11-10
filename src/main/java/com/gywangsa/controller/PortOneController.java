package com.gywangsa.controller;
import com.gywangsa.service.PdInfoService;
import com.gywangsa.service.PortOneService;
import com.siot.IamportRestClient.IamportClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Log4j2
public class PortOneController {

    private final PortOneService portOneService;
    private final PdInfoService pdInfoService;

    private IamportClient iamportClient;

    @Value("${portOne.key}")
    private String apiKey;

    @Value("${portOne.secret}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    @PostMapping("/cancelOrder/{deliNo}")
    public ResponseEntity<String> cancelPaymentByDeliNo(@PathVariable("deliNo") String deliNo,
                                                        RuntimeException e) throws IOException {

        log.info("-------------------PortOneController-------------------");
        log.info("============주문 취소============");

        String token = portOneService.getToken(apiKey, secretKey);
        portOneService.refundRequest(token, deliNo, e.getMessage());

        return ResponseEntity.ok().build();
    }


}
