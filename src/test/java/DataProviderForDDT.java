import org.testng.annotations.DataProvider;

public class DataProviderForDDT {
    @DataProvider(name = "dataForPost")   //this function works as testng data provider
    public Object[][] dataForPost() {
//        Object[][] data = new Object[3][2];
//
//        data[0][0] = "shihab";
//        data[0][1] = "SQA";
//
//        data[1][0] = "Imrul";
//        data[1][1] = "SQA";
//
//        data[2][0] = "Bushra";
//        data[2][1] = "SQA";
//
//        return data;

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
