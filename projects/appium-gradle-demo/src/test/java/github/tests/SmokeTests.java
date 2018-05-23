package github.tests;

import github.objects.IssueDetail;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SmokeTests {

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://api.github.com/repos/dtopuzov/test/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void getIssuesInRepo() {
        IssueDetail[] openIssues = given()
                .param("state", "open")
                .when()
                .get("/issues")
                .then()
                .statusCode(200)
                .extract().response().getBody().as(IssueDetail[].class);

        System.out.println("Open issues count: " + openIssues.length);

        IssueDetail[] closedIssues = given()
                .param("state", "closed")
                .when()
                .get("/issues")
                .then()
                .statusCode(200)
                .extract().response().getBody().as(IssueDetail[].class);

        System.out.println("Closed issues count: " + closedIssues.length);
    }

    @Test
    public void createIssue() {
        IssueDetail issue = new IssueDetail();
        issue.setTitle("Critical bug");
        issue.setBody("This is a critical bug.");

        IssueDetail result = given()
                .header("Authorization", "token " + System.getenv("GITHUB_PERSONAL_TOKEN"))
                .body(issue)
                .when()
                .post("/issues")
                .then()
                .statusCode(201)
                .extract().response().getBody().as(IssueDetail.class);

        System.out.println("Id of created issue: " + result.getId());
    }
}
