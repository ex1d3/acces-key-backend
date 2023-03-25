package com.example.accesskeybackend.webUtils.controller;

import com.example.accesskeybackend.webUtils.dto.CheckIPv6SupportDto;
import com.example.accesskeybackend.webUtils.service.CheckIPv6SupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CheckIPv6SupportController {

    private final CheckIPv6SupportService service;

    public CheckIPv6SupportController(CheckIPv6SupportService service) {
        this.service = service;
    }

    @GetMapping("/api/web/checkIpv6Support")
    public ResponseEntity<CheckIPv6SupportDto> response(@RequestParam("siteUrl") String siteUrl) {
        CheckIPv6SupportDto status = new CheckIPv6SupportDto();

        status.setSuccess(service.checkIPv6DnsRecord(siteUrl));
        return ResponseEntity.ok(status);
    }
}


