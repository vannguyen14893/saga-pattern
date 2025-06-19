package org.saga.languages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
    @GetMapping(value = "/response/xml",produces = "application/xml")
    public ResponseEntity<String> testResponseXml() {
        String xmlResponse = "<root>\n" +
                "  <welcome>Welcome to our application!</welcome>\n" +
                "  <buttons>\n" +
                "    <submit>Submit</submit>\n" +
                "    <cancel>Cancel</cancel>\n" +
                "  </buttons>\n" +
                "  <login>\n" +
                "    <button>Login</button>\n" +
                "  </login>\n" +
                "</root>";
        return ResponseEntity.ok(xmlResponse);
    }

    @GetMapping("/response/json")
    public ResponseEntity<String> testResponseJson() {
        String jsonResponse = "{\n" +
                "  \"welcome\": \"Welcome to our application!\",\n" +
                "  \"buttons\": {\n" +
                "    \"submit\": \"Submit\",\n" +
                "    \"cancel\": \"Cancel\"\n" +
                "  },\n" +
                "  \"login\": {\n" +
                "    \"button\": \"Login\"\n" +
                "  }\n" +
                "}";
        return ResponseEntity.ok(jsonResponse);
    }
}
