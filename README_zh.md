# unimail-java-sdk

> 当前sdk的版本是v2, 如果你需要用以前的v1版本, 请切换v1分支

unimail 的 java 语言 sdk, 快速集成到你的项目

[english docs](README.md)

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [unimail-java-sdk](#unimail-java-sdk)
  - [使用](#使用)
  - [api docs](#api-docs)
  - [support language## 支持的语言](#support-language-支持的语言)

<!-- /code_chunk_output -->

## 使用

- 初始化客户端

添加 maven 依赖

pom.xml

```xml

<dependency>
    <groupId>top.allcloud</groupId>
    <artifactId>unimail-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

- 发邮件

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

## support language## 支持的语言

sdk 默认返回的 msg 为中文

- [x] english (en)
- [x] simple chinese (zh)
- [x] vietnamese (vi)
- [x] idonesian (id)
- [x] thai (th)
- [x] gujarati (gu)

如果你需要添加了更多语言，欢迎提 issue

- 提示

如果想要使用 unimail sdk, 请通过邮件联系作者 i-curve@qq.com
