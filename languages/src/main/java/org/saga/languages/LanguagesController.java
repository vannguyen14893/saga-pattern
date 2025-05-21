package org.saga.languages;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("test")
public class LanguagesController {
    private final ResourceLoader resourceLoader;

    public LanguagesController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

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
}
