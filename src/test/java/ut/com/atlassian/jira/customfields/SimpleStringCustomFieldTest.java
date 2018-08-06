package ut.com.atlassian.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import org.junit.*;
import com.atlassian.jira.customfields.impl.SimpleStringCustomField;

/**
 * @since 3.5
 */
public class SimpleStringCustomFieldTest {

    private static SimpleStringCustomField simpleStringCustomField;

    @BeforeClass
    public static void setup() {
        simpleStringCustomField = new SimpleStringCustomField(null, null);
    }

    @Test
    public void testGetSingularObjectFromString() throws Exception {
        Assert.assertEquals("(a)", simpleStringCustomField.getSingularObjectFromString("(a)"));
        Assert.assertEquals(null, simpleStringCustomField.getSingularObjectFromString(null));
    }

    @Test(expected=FieldValidationException.class)
    public void testGetSingularObjectFromStringException() throws Exception {
        Assert.assertEquals("(a)", simpleStringCustomField.getSingularObjectFromString("(a))"));
    }

}
