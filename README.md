# unimail-java-sdk

> The current SDK version is v2. If you need to use the previous v1 version, please switch to the v1 branch.

This is a Java SDK for Unimail. Quickly integrate into your project

[中文文档](README_zh.md)

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [unimail-java-sdk](#unimail-java-sdk)
  - [usage](#usage)
  - [api docs](#api-docs)
  - [support language](#support-language)

<!-- /code_chunk_output -->

## usage

- init a unimail client

add the package

pom.xml

```xml
<dependency>
    <groupId>top.allcloud</groupId>
    <artifactId>unimail-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

- send email

```java

public static void main(String[] args) {
    UnimailClient client = new UnimailClient(key);

    // check the client connection
    boolean status = client.checkConnection();
    System.out.println(status);

    // send email
    UniResponse uniResponse = client.sendEmail("aaa@gmail.com", "email subject", "this is a email content");
    System.out.println(uniResponse.isSuccess());

    if (!uniResponse.isSuccess()) {
        System.out.println(uniResponse.msg);
    }
}
```

## api docs

1. UnimailClient New(key string)

init a client by key

2. void client.setHost(host string)

set host for the client

3. boolean client.setLang(lang string)

set language for the client,default is zh

4. boolean client.CheckConnect()

check the host and key is ok

5. UniResponse client.SendEmail(String receiver,String subject,String content)

## support language

chinese is the default language for the sdk.

- [x] english (en)
- [x] simple chinese (zh)
- [x] vietnamese (vi)
- [x] idonesian (id)
- [x] thai (th)
- [x] gujarati (gu)

if you want to support other language, please open a issue.

- tips

> If you want to use this SDK, please contact the author via i-curve@qq.com.
