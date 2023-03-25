package com.example.accesskeybackend.webUtils.service;

import org.springframework.stereotype.Service;

import java.net.*;
import java.util.Optional;

@Service
public class CheckIPv6SupportService {

    private static String D_SCHEME = "https://";

    public boolean checkIPv6DnsRecord(String siteUrl) {
        try {
            siteUrl = formatUrl(siteUrl).orElse("");
            final InetAddress[] resolvedAddresses = InetAddress.getAllByName(siteUrl);
            for (final InetAddress address : resolvedAddresses) {
                if (address instanceof Inet6Address) {
                    return true;
                }
            }
        } catch (UnknownHostException ignored) {
        }

        return false;
    }

    private static Optional<String> formatUrl(String siteUrl) throws UnknownHostException {

        URI uri;

        try {
            uri = new URI(siteUrl);
            final String scheme = uri.getScheme();

            if(scheme == null){
                uri = new URI(D_SCHEME + siteUrl);
            }

            return Optional.ofNullable(uri.getHost());
        } catch (final  URISyntaxException ignored) {
            return Optional.empty();
        }
    }
}
