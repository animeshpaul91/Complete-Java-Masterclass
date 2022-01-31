package io.dropwizard;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropWizard_ProjectApplication extends Application<DropWizard_ProjectConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropWizard_ProjectApplication().run(args);
    }

    @Override
    public String getName() {
        return "Hello-World";
    }

    @Override
    public void initialize(final Bootstrap<DropWizard_ProjectConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropWizard_ProjectConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
