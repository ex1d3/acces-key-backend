package com.example.accesskeybackend.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;


@RestController
public class CheckIPv6SupportController {
    @GetMapping("/api/web/checkIpv6Support")
    public String Respone(@RequestParam("siteUrl") String url) {
        if(Check(url)) { return "Success"; }
        else { return "Failure"; }
    }

    public String UrlFormat(String url) {
        if(url.startsWith("www.")) {
            url = url.split("\\.")[1] + "." + url.split("\\.")[2].split("/")[0];
        } else if(url.startsWith("https://www.") | url.startsWith("http://www.")) {
            url = url.substring(url.indexOf('.') + 1);
        } else if(url.startsWith("https://") | url.startsWith("http://")) {
            url = url.substring(url.indexOf('/'));
        }

        if(url.contains("/")) { url = url.split("/")[0]; }

        return url;
    }
    public boolean Check(String url) {
        try
        {
            url = UrlFormat(url);

            InitialDirContext iDirC = new InitialDirContext();
            Attributes attributes = iDirC.getAttributes("dns:/" + url);
            NamingEnumeration<? extends Attribute> attributeEnumeration = attributes.getAll();

            while (attributeEnumeration.hasMore()) {
                String line = attributeEnumeration.next().toString();
                if(line.substring(0, 4).contains("AAAA")){ return true; }
            }

            attributeEnumeration.close();
        }
        catch (NamingException ignored) {}

        return false;
    }
}
