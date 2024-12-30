# unimail-java-sdk

unimail 的 java 语言 sdk, 快速集成到你的项目

[english docs](README.md)

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [unimail-go-sdk](#unimail-go-sdk)
    - [简单使用](#简单使用)
    - [api docs](#api-docs)
    - [support language## 支持的语言](#support-language-支持的语言)

<!-- /code_chunk_output -->

## 简单使用

- 初始化客户端

添加 maven 依赖

pom.xml

```xml

<dependency>
    <groupId>space.i-curve</groupId>
    <artifactId>unimail-client</artifactId>
    <version>${{latest version}}</version>
</dependency>
```

- 发邮件

例如
收件人: aaa@gmail.com  
邮件标题: email subject  
邮件正文: this is a email content

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

- 批量发送邮件

例如
收件人: aaa@gmail.com,bbb@gmail.com  
邮件标题: email subject  
邮件正文: this is a email content

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

## support language## 支持的语言

sdk 默认返回的 msg 为中文

- [x] english (en)
- [x] simple chinese (zh)
- [x] vietnamese (vi)
- [x] idonesian (id)
- [x] thai (th)
- [x] gujarati (gu)

如果你需要添加了更多语言，欢迎提 issue
