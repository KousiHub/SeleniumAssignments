import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class FirstWireMockProgram {
    public static void main(String[] args) {
        stubFor(get(urlEqualTo("/greet"))
                .willReturn(aResponse()
                .withHeader("Content-Type","text/plain")
                .withBody("Hello world")));
    }
}
