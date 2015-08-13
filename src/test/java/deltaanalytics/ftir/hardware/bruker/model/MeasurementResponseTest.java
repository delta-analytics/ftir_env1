package deltaanalytics.ftir.hardware.bruker.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MeasurementResponseTest {
    @Test
    public void getDAP(){
        MeasurementResponse measurementResponse = new MeasurementResponse("OK 1 \"C:\\OPUS_7.0.129\\MEAS3\\Test24.1\" 1 654 0");
        assertThat(measurementResponse.getDAP(), is(equalTo("C:\\OPUS_7.0.129\\MEAS3")));
    }
    @Test
    public void getSAN(){
        MeasurementResponse measurementResponse = new MeasurementResponse("OK 1 \"C:\\OPUS_7.0.129\\MEAS3\\Test24.1\" 1 654 0");
        assertThat(measurementResponse.getSAN(), is(equalTo("Test24.1")));
    }
}
