package it;

import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

public class IntegrationTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }
//
//    @Test
//    public void GetAllStudent() {
//        StudentRepository repository = app.injector().instanceOf(StudentRepository.class);
//        repository.createStudent(new StudentData("Alex", 15));
//
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/v1/posts");
//
//        Result result = route(app, request);
//
//        assertEquals(200, result.status());
//
//        JsonNode listOfPosts = contentAsJson(result);
//        Optional<StudentResource> student = findStudentByName(listOfPosts, "Alex");
//        assertTrue(student.isPresent());
//    }
//
//    private Optional<StudentResource> findStudentByName(JsonNode listOfPosts, String studentName) {
//        Iterator<JsonNode> elements = listOfPosts.elements();
//        // spliterator dance to build a Stream from an Iterator
//        return StreamSupport.stream(
//            Spliterators.spliteratorUnknownSize(
//                elements,
//                Spliterator.ORDERED),
//            false)
//            .map(jsonNode -> Json.fromJson(jsonNode, StudentResource.class))
//            .filter(p -> {
//                return p.getName().equals(studentName);
//            })
//            .findFirst();
//    }
//
//    private JsonNode contentAsJson(Result result) {
//        final String responseBody = contentAsString(result);
//        return Json.parse(responseBody);
//    }
//
//    @Test
//    public void testListWithTrailingSlashIsUnknown() {
//        PostRepository repository = app.injector().instanceOf(PostRepository.class);
//        repository.create(new PostData("title-of-another-post", "body-456"));
//
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/v1/posts/");
//
//        Result result = route(app, request);
//        assertEquals(404, result.status());
//    }
//
//    @Test
//    public void testTimeoutOnUpdate() {
//        PostRepository repository = app.injector().instanceOf(PostRepository.class);
//        repository.create(new PostData("title-testTimeoutOnUpdate", "body-testTimeoutOnUpdate"));
//
//        JsonNode json = Json.toJson(new PostResource("1", "http://localhost:9000/v1/posts/1", "some title", "somebody"));
//
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(PUT)
//                .bodyJson(json)
//                .uri("/v1/posts/1");
//
//        Result result = route(app, request);
//        org.hamcrest.MatcherAssert.assertThat(result.status(), equalTo(GATEWAY_TIMEOUT));
//    }
//
//    @Test
//    public void testCircuitBreakerOnShow() {
//        PostRepository repository = app.injector().instanceOf(PostRepository.class);
//        repository.create(new PostData("title-testCircuitBreakerOnShow", "body-testCircuitBreakerOnShow"));
//
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/v1/posts/1");
//
//        Result result = route(app, request);
//        org.hamcrest.MatcherAssert.assertThat(result.status(), equalTo(SERVICE_UNAVAILABLE));
//    }


}
