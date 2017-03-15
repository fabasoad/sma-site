import org.fabasoad.api.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author efabizhevsky
 * @date 3/15/2017.
 */
public class LoggerTest {

    @Test
    public void testSingleton() {
        org.fabasoad.log.Logger logger1 = Logger.getLogger();
        assertNotNull(logger1);

        org.fabasoad.log.Logger logger2 = Logger.getLogger();
        assertNotNull(logger2);

        assertEquals(logger1, logger2);
    }
}
