package org.saga.languages;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
@Slf4j
public class LanguagesController {

    private final ResourceLoader resourceLoader;
    private final ApiAdapter apiAdapter;

    @GetMapping("/translations/{lng}/{ns}.json")
    public ResponseEntity<String> test(@PathVariable String lng,
                                       @PathVariable String ns) throws IOException {

        Resource resource = resourceLoader.getResource(
                "classpath:/static/locales/" + lng + "/" + ns + ".json");

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String content = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(content);
    }

    @GetMapping("/document/{name}.json")
    public ResponseEntity<String> data(@PathVariable String name) throws IOException {

        Resource resource = resourceLoader.getResource(
                "classpath:/static/locales/" + name + ".json");

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String content = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(content);
    }

    @PostMapping("/translations/add/{lng}/{ns}")
    public ResponseEntity<Void> addMissingTranslation(
            @PathVariable String lng,
            @PathVariable String ns,
            @RequestBody String payload) {

        // Here you would typically save missing translations to a database
        System.out.printf("Missing translation: %s.%s.%s%n", lng, ns, payload);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/translations")
    public ResponseEntity<String> test02(@RequestBody RequestPayload payload) throws IOException {
        log.info("payload {}", payload);
        Resource resource = resourceLoader.getResource(
                "classpath:/static/locales/en/common.json");

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String content = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(content);
    }
    @PostMapping("/adapter")
    public ResponseEntity<String> testAdapter02(@RequestBody String payload) {
        String joltSpecJson = "[\n" +
                "    {\n" +
                "        \"operation\": \"shift\",\n" +
                "        \"spec\": {\n" +
                "            \"common\": {\n" +
                "                \"welcome\": \"convert_common.welcome\",\n" +
                "                \"buttons\": {\n" +
                "                    \"submit\": \"convert_common.buttons.submit\",\n" +
                "                    \"cancel\": \"convert_common.buttons.cancel\",\n" +
                "                    \"login\": \"convert_common.buttons.login\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"operation\": \"default\",\n" +
                "        \"spec\": {}\n" +
                "    }\n" +
                "]";
        String joltSpecJsonRes = "[\n" +
                "    {\n" +
                "        \"operation\": \"shift\",\n" +
                "        \"spec\": {\n" +
                "            \"welcome\": \"common.welcome\",\n" +
                "            \"buttons\": {\n" +
                "                \"submit\": \"common.buttons.submit\",\n" +
                "                \"cancel\": \"common.buttons.cancel\"\n" +
                "            },\n" +
                "            \"login\": {\n" +
                "                \"button\": \"common.buttons.login\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"operation\": \"default\",\n" +
                "        \"spec\": {}\n" +
                "    }\n" +
                "]";
        ApiAdapter.ConfigApi configApi = new ApiAdapter.ConfigApi();
        configApi.setMethod("POST");
        configApi.setType("xml");
        configApi.setUrl("http://localhost:8089/test/translations");
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json",
                "Accept", "application/json"

        );
        configApi.setHeaders(headers);
        configApi.setRequestTemplate(joltSpecJson);
        configApi.setResponseTemplate(joltSpecJsonRes);
        configApi.setRequest(payload);
        String adapter = apiAdapter.adapter(configApi);
        return ResponseEntity.ok(adapter);
    }

    @GetMapping("/adapter")
    public ResponseEntity<String> testAdapter() {
        String joltSpecJson = "[\n" +
                "    {\n" +
                "        \"operation\": \"shift\",\n" +
                "        \"spec\": {\n" +
                "            \"welcome\": \"common.welcome\",\n" +
                "            \"buttons\": {\n" +
                "                \"submit\": \"common.buttons.submit\",\n" +
                "                \"cancel\": \"common.buttons.cancel\"\n" +
                "            },\n" +
                "            \"login\": {\n" +
                "                \"button\": \"common.buttons.login\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"operation\": \"default\",\n" +
                "        \"spec\": {}\n" +
                "    }\n" +
                "]";
        ApiAdapter.ConfigApi configApi = new ApiAdapter.ConfigApi();
        configApi.setMethod("GET");
        configApi.setType("xml");
        configApi.setUrl("http://localhost:8089/response/xml");
        Map<String, String> headers = Map.of(
                "Content-Type", "application/xml"

        );
        configApi.setHeaders(headers);
        configApi.setResponseTemplate(joltSpecJson);
        String adapter = apiAdapter.adapter(configApi);
        return ResponseEntity.ok(adapter);
    }
}
