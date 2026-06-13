# unimail-java-sdk

> The current branch SDK version is v1. This branch will no longer receive functional updates. For new users, please
> refer to the main branch "master".

This is a Java SDK for Unimail. Quickly integrate into your project

[中文文档](README_zh.md)

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [unimail-go-sdk](#unimail-go-sdk)
    - [simple usage](#simple-usage)
    - [api docs](#api-docs)
    - [support language](#support-language)

<!-- /code_chunk_output -->

## simple usage

- init a unimail client

add the package

pom.xml

```xml

<dependency>
    <groupId>space.i-curve</groupId>
    <artifactId>unimail-client</artifactId>
    <version>0.3.0</version>
</dependency>
```

- send email

example
receiver: aaa@gmail.com  
email subject: email subject  
email content: this is a email content

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

- batch send email

example
receivers: aaa@gmail.com,bbb@gmail.com  
email subject: email subject  
email content: this is a email content

```java
public static void main(String[] args) {
    UnimailClient client = new UnimailClient(key);

    // check the client connection
    boolean status = client.checkConnection();
    System.out.println(status);

    uniResponse = client.batchSendEmail(List.of("aaa@gmail.com", "bbb@gmail.com"), "email subject", "this is a email content");
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

send email to receiver. if you have many receiver, you can concat the receiver by ";" or use BatchSendEmail

6. UniResponse client.BatchSendEmail(List<String> receivers,String subject,String content)

like SendEmail, but receivers is a slice

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