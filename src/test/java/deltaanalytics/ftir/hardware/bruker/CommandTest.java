package deltaanalytics.ftir.hardware.bruker;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandTest {
    @Test
    public void containsMessageAndTimeStampsAndParameters(){
        Command command = new Command();
        command.setMessage("Message");
        LocalDateTime now = LocalDateTime.now();
        command.setExecutionStartedAt(now);
        command.setExecutionFinishedAt(now.plusMinutes(1));
        ParameterMap parameterMaps = new ParameterMap();
        command.setParameterMap(parameterMaps);

        assertThat(command.getMessage(), is(notNullValue()));
        assertThat(command.getExecutionStartedAt(), is(now));
        assertThat(command.getExecutionFinishedAt(), is(now.plusMinutes(1)));
        assertThat(command.getExecutionFinishedAt(), is(now.plusMinutes(1)));
        assertThat(command.getParameterMap(), is(notNullValue()));
    }
}
