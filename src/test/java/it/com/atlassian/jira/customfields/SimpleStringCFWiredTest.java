package it.com.atlassian.jira.customfields;


import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.customfields.impl.SimpleStringCustomField;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AtlassianPluginsTestRunner.class)
public class SimpleStringCFWiredTest
{
    private final SimpleStringCustomField simpleStringCustomField;
    private final ApplicationProperties applicationProperties;

//    Logger logger = LoggerFactory.getLogger(SimpleStringCFWiredTest.class);


    public SimpleStringCFWiredTest(@ComponentImport ApplicationProperties applicationProperties,
                                   @ComponentImport SimpleStringCustomField simpleStringCustomField)
    {
        this.simpleStringCustomField = simpleStringCustomField;
        this.applicationProperties = applicationProperties;
    }

    @Test
    public void testComponentExist()
    {
        Assert.assertNotNull(applicationProperties);
        Assert.assertNotNull(simpleStringCustomField);
    }

    @Test(expected = FieldValidationException.class)
    public void testCustomField()
    {
        simpleStringCustomField.getSingularObjectFromString("a())");
    }

}