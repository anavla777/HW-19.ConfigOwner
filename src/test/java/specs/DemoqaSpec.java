package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.AllureListener.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DemoqaSpec {
    public static final RequestSpecification demoqaRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static final ResponseSpecification demoqaOkResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();

    public static final ResponseSpecification demoqaCreatedResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .build();
}
