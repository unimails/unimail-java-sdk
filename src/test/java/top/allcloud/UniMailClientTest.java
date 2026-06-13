package top.allcloud;

import org.junit.Test;

import java.io.FileNotFoundException;

public class UniMailClientTest {
    private UnimailClient client = new UnimailClient("");

    @Test
    public void testConnection() {
        UniResponse uniResponse = client.checkConnection();
        System.out.println(uniResponse + " " + uniResponse.isSuccess());
    }

    @Test
    public void testSendEmail() throws FileNotFoundException {
        UnimailReq req = new UnimailReq();
        req.receivers.add("email");
        req.subject = "java test";
        req.txtContent = "this is a java client test email.";
        req.appendFile("att1.txt", "./attachment.txt");
        UniResponse uniResponse = client.sendEmail(req);
        System.out.println(uniResponse);
    }
}
