package top.allcloud;

import java.io.InputStream;

public class EmailAttachment {
    public String name;
    public InputStream fileAttachment;
    public String urlAttachment;

    public EmailAttachment() {
    }

    public EmailAttachment(String name, InputStream fileAttachment) {
        this.name = name;
        this.fileAttachment = fileAttachment;
    }

    public EmailAttachment(String name, String urlAttachment) {
        this.name = name;
        this.urlAttachment = urlAttachment;
    }
}
