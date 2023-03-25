package com.example.accesskeybackend.webUtils.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class CheckIPv6SupportService {

    public boolean checkIPv6DnsRecord(String url) {
        try {
            String domain = formatUrl(url);
            final InetAddress[] resolvedAddresses = InetAddress.getAllByName(domain);

            for (final InetAddress address : resolvedAddresses) {
                if (address instanceof Inet6Address) {
                    return true;
                }
            }
        } catch (UnknownHostException ignored) {
        }

        return false;
    }

    private String formatUrl(String siteUrl) {
        if(siteUrl.startsWith("https://") | siteUrl.startsWith("http://")) {
            siteUrl = siteUrl.substring(siteUrl.indexOf('/') + 2);
        }
        if (siteUrl.contains("/")) {
            siteUrl = siteUrl.split("/")[0];
        }

        return siteUrl;
    }
}
