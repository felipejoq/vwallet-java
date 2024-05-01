package tests.utils.strings;

import org.junit.jupiter.api.Test;
import utils.strings.StringUtils;

public class StringUtilsTest {

    @Test
    public void testGetRandomString() {
        String randomString = StringUtils.getRandomString(6);

        assert (randomString.matches("[A-Z0-9-]+"));
        assert (randomString.length() == 7);
    }

}
