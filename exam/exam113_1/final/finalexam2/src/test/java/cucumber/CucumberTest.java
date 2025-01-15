package cucumber;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.ConfigurationParameter;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("src/test/resources/cucumber/cal.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "cucumber")
public class CucumberTest {
}
