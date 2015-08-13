package deltaanalytics.ftir.hardware.bruker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BrukerConfigurationParameterOption {
    private String value;
    private String key;
    private Long id;
    private String description;
    private String locale;

    public BrukerConfigurationParameterOption() {
    }

    public BrukerConfigurationParameterOption(String key, String description) {
        this.key = key;
        this.description = description;
    }

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
