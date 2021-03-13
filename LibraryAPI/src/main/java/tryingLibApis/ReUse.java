package tryingLibApis;

import io.restassured.path.json.JsonPath;

public class ReUse {
    public static JsonPath rawToJson(String response){
        JsonPath jp =new JsonPath(response);
        return jp;
    }
}
