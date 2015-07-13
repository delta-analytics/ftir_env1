package deltaanalytics.ftir.hardware.bruker.controller;

import deltaanalytics.ftir.hardware.bruker.controller.OpusHttpCaller;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class OpusHttpCallerTest {
    @Test
    public void creation(){
        OpusHttpCaller opusHttpCaller = new OpusHttpCaller();

        assertThat(opusHttpCaller, is(notNullValue()));
    }
}
