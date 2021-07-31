package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class AppTest {
    @Test
    public void differTest_withFlatJson() throws Exception {
        Map<String, Object> firstJson = new HashMap<>(Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false
        ));

        Map<String, Object> secondJson = new HashMap<>(Map.of(
                "timeout", 20,
                "verbose", true,
                "host", "hexlet.io"
        ));

        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";

        Assertions.assertEquals(Differ.generate(firstJson, secondJson), expected);
    }
}
