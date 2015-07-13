package deltaanalytics.ftir.hardware.bruker.controller;

import deltaanalytics.ftir.Main;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameterOption;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@Transactional
public class BrukerConfigurationServiceTest {

    @Autowired
    private BrukerConfigurationParameterRepository brukerConfigurationParameterRepository;
    @Autowired
    private BrukerConfigurationService brukerConfigurationService;

    @Test
    public void initializeWithDefaults() {
        brukerConfigurationService.initializeBrukerConfiguration();

        assertThat(brukerConfigurationParameterRepository.count(), is(equalTo(24L)));

    }

    @Test
    public void readWithOptions() {
        brukerConfigurationService.initializeBrukerConfiguration();

        BrukerConfigurationParameter brukerConfigurationParameter = brukerConfigurationParameterRepository.findByKey("AQM");
        assertThat(brukerConfigurationParameter.getDescription(), is(equalTo("Akquisition Mode")));
        List<BrukerConfigurationParameterOption> options = brukerConfigurationParameter.getBrukerConfigurationParameterOptions();

        assertThat(options.size(), is(equalTo(6)));

        brukerConfigurationParameter = brukerConfigurationParameterRepository.findByKey("APF");
        assertThat(brukerConfigurationParameter.getDescription(), is(equalTo("Apodization Function")));
        options = brukerConfigurationParameter.getBrukerConfigurationParameterOptions();

        assertThat(options.size(), is(equalTo(8)));
    }
}