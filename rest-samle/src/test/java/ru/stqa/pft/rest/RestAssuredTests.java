package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

//import org.apache.http.client.fluent.Executor;
//import org.apache.http.client.fluent.Request;
//import org.testng.annotations.Test;


public class RestAssuredTests {
  @BeforeClass
  public void init() {
    RestAssured.authentication=RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed","");
  }

  @Test //(enabled = false)
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues =getIssues();
    Issue newIssue =new Issue().withSubject("Test issue").withDescription("New test issue description");
    int issueId=createIssue(newIssue); // метод возвращает идентификатор нового баг-репорта
    Set<Issue> newIssues =getIssues();
    oldIssues.add(newIssue.withId(issueId));
  }
  @Test
  public void testDeleteTask() throws IOException {
    String message=getMessage();
    assertEquals(message,"Параметр email_owner является обязательным!");


  }
  private Set<Issue> getIssues() throws IOException {
    // немного другой способ работы c http клиентом использующим fluent интерфейс
    //String json=getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
    //        .returnContent().asString();
    String json=RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new  JsonParser().parse(json); // на выходе json элемент
    JsonElement issues =parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken <Set <Issue>>(){}.getType());

  }
  private String  getMessage() throws IOException {
    String json=getExecutorMy().execute(Request.Get("http://users.bugred.ru/tasks/rest/deletetask"))
            .returnContent().asString();
    JsonElement parsed = new  JsonParser().parse(json); // на выходе json элемент
    JsonElement element =parsed.getAsJsonObject().get("message");
    return new Gson().fromJson(element,String.class); // просто строка

  }

  //private Executor getExecutor() {
  //  return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
  //}

  private Executor getExecutorMy() {
    //return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
    //return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
    return Executor.newInstance().auth("manager@mail.ru ", "1");
  }


  private int createIssue(Issue newIssue) throws IOException {
    //String json=getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")
    //         .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject() ),
    //                   new BasicNameValuePair("description",newIssue.getDescription() )))
    //        .returnContent().asString();
    String json=RestAssured.given()
            .parameter("subject",newIssue.getSubject())
            .parameter("description",newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();

    JsonElement parsed = new  JsonParser().parse(json); // на выходе json элемент
    JsonElement issues =parsed.getAsJsonObject().get("issues");
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }



}
