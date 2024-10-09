package space.i_curve;

import org.junit.Test;

public class UniMailClientTest {
    @Test
    public void testSendEmail() {
        UniMailClient client = new UniMailClient("http://127.0.0.1:8080/api/email/sendEmail", "d887fb95-7a01-471a-a5ac-558ba5c100d6");
        UniResponse uniResponse = client.sendEmail("test@qq.com", "test", "test");
        System.out.println(uniResponse.data);
    }
}
