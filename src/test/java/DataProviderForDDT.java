import org.testng.annotations.DataProvider;

public class DataProviderForDDT {
    @DataProvider(name = "dataForPost")   //this function works as testng data provider
    public Object[][] dataForPost() {
        return new Object[][] {
                {"Shihab", "SQA"},
                {"Imrul", "SQA"},
                {"Bushra", "SQA"}
        };
    }

    @DataProvider(name = "dataForDelete")   //this function works as testng data provider
    public Object[] dataForDelete() {
        return new Object[] {
                2,3,4
        };
    }
}
