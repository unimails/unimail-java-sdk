package top.allcloud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UnimailReq {
    /**
     *
     */
    public String from;
    /**
     * 收件邮箱: 必填
     */
    public List<String> receivers = new ArrayList<>();
    /**
     * 抄送人: 可空
     */
    public String cc;
    /**
     * 密送人: 可空
     */
    public String bcc;
    public String subject;
    /**
     * 和 htmlContent 二选一，优先使用 htmlContent
     */
    public String txtContent;
    /**
     * 和 txtContent 二选一，优先使用 htmlContent
     */
    public String htmlContent;
    /**
     * 附件
     */
    public List<EmailAttachment> attachments = new ArrayList<>();

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<EmailAttachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * 添加附件
     *
     * @param attachment 附件资源(InputStream或者URI)
     */
    public void appendAttachment(EmailAttachment attachment) {
        if (attachment != null) {
            this.attachments.add(attachment);
        }
    }

    /**
     * 添加文件附件
     *
     * @param name 上传后的附件名
     * @param path 实际文件路径
     * @throws FileNotFoundException 文件未找到异常
     */
    public void appendFile(String name, String path) throws FileNotFoundException {
        this.attachments.add(new EmailAttachment(name, new FileInputStream(path)));
    }

    /**
     * 添加uri附件
     *
     * @param name 上传后的附件名
     * @param url  http uri 资源路径
     */
    public void appendUri(String name, String url) {
        this.attachments.add(new EmailAttachment(name, url));
    }
}
