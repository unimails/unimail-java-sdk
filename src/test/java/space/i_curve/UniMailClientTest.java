package space.i_curve;

import org.junit.Test;

import java.util.ArrayList;

public class UniMailClientTest {
    private UnimailClient client = new UnimailClient("");

    @Test
    public void testConnection() {
        boolean status = client.checkConnection();
        System.out.println(status);
    }

    @Test
    public void testSendEmail() {
        UniResponse uniResponse = client.sendEmail("i-curve@qq.com", "java test", "this is a java client test email.");
        System.out.println(uniResponse);
    }

    @Test
    public void testBatchSendEmail() {
        ArrayList<String> receivers = new ArrayList<>();
        receivers.add("i-curve@qq.com");
        receivers.add("i_curve@qq.com");
        UniResponse uniResponse = client.batchSendEmail(receivers, "java test", "this is a java client test email.");
        System.out.println(uniResponse);
    }
}
