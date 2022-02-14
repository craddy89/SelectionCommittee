package util.matcher;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UrlMatcherTest {

    private String expectedResultString;

    @BeforeClass
    public void setUp() {
        expectedResultString="Controller?command=action";
    }

    @DataProvider(name="UrlTestPreString")

    public Object[][] provideTestUrlString() {
        return new Object[][]{{"https://someAddress/"}, {"https://someAddress/jdIjs/"}, {"https://"}};
    }


    @Test(dataProvider = "UrlTestPreString")
    public void testMatchUrl(String urlTestPreString) {
        String  urlTestString=urlTestPreString+expectedResultString;
        String actualResultString=UrlMatcher.matchUrl(urlTestString);

        assertEquals(actualResultString,expectedResultString);
    }
}
