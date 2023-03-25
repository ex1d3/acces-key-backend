package com.example.accesskeybackend.webUtils.controller;

import com.example.accesskeybackend.webUtils.service.CheckIPv6SupportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class CheckIPv6SupportController {

    private final CheckIPv6SupportService service;

    public CheckIPv6SupportController(CheckIPv6SupportService service) {
        this.service = service;
    }

    @GetMapping("/api/web/checkIpv6Support")
    public ResponseEntity<String> response(@RequestParam("siteUrl") String siteUrl) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (service.checkIPv6DnsRecord(siteUrl)) {
            String responseBody = mapper.writeValueAsString(Map.of("success", true));
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(mapper.writeValueAsString(Map.of("failure", false)));
        }
    }
}


