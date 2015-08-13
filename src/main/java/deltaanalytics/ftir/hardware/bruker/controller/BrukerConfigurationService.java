package deltaanalytics.ftir.hardware.bruker.controller;

import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameterOption;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameterOptionRepository;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
@Transactional
public class BrukerConfigurationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrukerConfigurationService.class);

    @Autowired
    private BrukerConfigurationParameterRepository brukerConfigurationParameterRepository;

    @Autowired
    private BrukerConfigurationParameterOptionRepository brukerConfigurationParameterOptionRepository;

    public void initializeBrukerConfiguration() {
        LOGGER.info("initializeBrukerConfiguration");
        try {
            LOGGER.info("Load src/main/resources/bruker_configuration_parameter_description_en.properties");
            loadBrukerConfigurationParameter();

            LOGGER.info("Load src/main/resources/bruker_configuration_parameter_options_en.properties");
            loadBrukerConfigurationParameterOption();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BrukerConfigurationParameter> readAll() {
        initializeBrukerConfiguration();
        List<BrukerConfigurationParameter> brukerConfigurationParameters = brukerConfigurationParameterRepository.findAll();
        LOGGER.info("brukerConfigurationParameters in DB " + brukerConfigurationParameters.size());
        return brukerConfigurationParameters;
    }

    private void loadBrukerConfigurationParameterOption() throws IOException {
        Properties brukerConfigurationParameterOptions = getProperties("src/main/resources/bruker_configuration_parameter_options_en.properties");
        for (Map.Entry<Object, Object> keyAndValue : brukerConfigurationParameterOptions.entrySet()) {
            LOGGER.info("" + keyAndValue);
            BrukerConfigurationParameterOption brukerConfigurationParameterOption = new BrukerConfigurationParameterOption((String) keyAndValue.getKey(), (String) keyAndValue.getValue());
            String parameterKey = ((String) keyAndValue.getKey()).split("\\.")[0];
            brukerConfigurationParameterOption.setValue((String) keyAndValue.getValue());
            brukerConfigurationParameterOption.setKey(((String) keyAndValue.getKey()));
            brukerConfigurationParameterOptionRepository.save(brukerConfigurationParameterOption);
            LOGGER.info("Add Option for " + parameterKey);
            BrukerConfigurationParameter brukerConfigurationParameter = brukerConfigurationParameterRepository.findByKey(parameterKey);
            brukerConfigurationParameter.addBrukerConfigurationParameterOption(brukerConfigurationParameterOption);
            brukerConfigurationParameterRepository.save(brukerConfigurationParameter);
        }
    }

    private void loadBrukerConfigurationParameter() throws IOException {
        Properties brukerConfigurationParameterProperties = getProperties("src/main/resources/bruker_configuration_parameter_description_en.properties");
        for (Map.Entry<Object, Object> keyAndValue : brukerConfigurationParameterProperties.entrySet()) {
            BrukerConfigurationParameter brukerConfigurationParameter = new BrukerConfigurationParameter((String) keyAndValue.getKey(), (String) keyAndValue.getValue());
            brukerConfigurationParameterRepository.save(brukerConfigurationParameter);
        }
    }

    private Properties getProperties(String name) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(name));
        new FileInputStream(name).close();
        return properties;
    }
}
