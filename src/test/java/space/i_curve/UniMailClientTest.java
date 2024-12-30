package space.i_curve;

import org.junit.Test;

import java.util.ArrayList;

public class UniMailClientTest {
    @Test
    public void testSendEmail() {
        UnimailClient client = new UnimailClient("");
        UniResponse uniResponse = client.sendEmail("i-curve@qq.com", "java test", "this is a java client test email.");
        System.out.println(uniResponse);
    }

    @Test
    public void testBatchSendEmail() {
        UnimailClient client = new UnimailClient("");
        ArrayList<String> receivers = new ArrayList<>();
        receivers.add("i-curve@qq.com");
        receivers.add("i_curve@qq.com");
        UniResponse uniResponse = client.batchSendEmail(receivers, "java test", "this is a java client test email.");
        System.out.println(uniResponse);
    }
}
