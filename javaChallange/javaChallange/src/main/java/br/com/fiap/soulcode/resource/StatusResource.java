package br.com.fiap.soulcode.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatusResource {

    @GetMapping("/status")
    public Map<String, String> verificarStatus() {
        return Map.of(
                "sistema", "SoulGuide",
                "status", "online"
        );
    }
}