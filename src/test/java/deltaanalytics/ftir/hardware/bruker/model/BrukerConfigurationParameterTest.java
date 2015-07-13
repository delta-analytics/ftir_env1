package deltaanalytics.ftir.hardware.bruker.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BrukerConfigurationParameterTest {
    @Test
    public void instantiate(){
        String key = "APT";
        String description = "OPEN";
        BrukerConfigurationParameter brukerConfigurationParameter = new BrukerConfigurationParameter(key, description);

        assertThat(brukerConfigurationParameter.getKey(), is(equalTo(key)));
        assertThat(brukerConfigurationParameter.getDescription(), is(equalTo(description)));
    }
}