package io.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class DropWizard_ProjectConfiguration extends Configuration {
    // TODO: implement service configuration

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Animesh";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
