package co.templex.oauth.test;

import co.templex.oauth.OAuthResponse;
import co.templex.oauth.Querier;
import co.templex.oauth.SuccessfulOAuthResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QuerierTest {

    @Test
    public void queryTest() throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties"));
        if (properties.getProperty("validtoken", null) == null || properties.getProperty("validtoken", null).equals("")) {
            return;
        }
        Querier querier = new Querier();
        OAuthResponse response = querier.query(properties.getProperty("validtoken", null));
        if (response != null && response instanceof SuccessfulOAuthResponse) {
            assertEquals(properties.getProperty("username", null), ((SuccessfulOAuthResponse) response).getUsername());
            assertEquals(properties.getProperty("uuid", null), ((SuccessfulOAuthResponse) response).getUuid());
            OAuthResponse failedResponse = querier.query(properties.getProperty("validtoken", null));
            if (failedResponse == null || failedResponse instanceof SuccessfulOAuthResponse) {
                fail();
            }
        } else {
            fail();
        }
    }

}
