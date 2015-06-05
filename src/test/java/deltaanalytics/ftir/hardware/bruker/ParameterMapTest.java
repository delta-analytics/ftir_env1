package deltaanalytics.ftir.hardware.bruker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ParameterMapTest {
    @Test
    public void addParameter(){
        ParameterMap parameterMap = new ParameterMap();
        String key = "ecc";
        String value = "1.2";
        parameterMap.add(key, value);

        assertThat(parameterMap.get(key), is(equalTo(value)));
    }
}