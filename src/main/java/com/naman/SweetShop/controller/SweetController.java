package com.naman.SweetShop.controller;

import com.naman.SweetShop.model.Sweet;
import com.naman.SweetShop.service.SweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    @PostMapping
    public ResponseEntity<Sweet> addSweet(@ResponseBody Sweet sweet) {
        Sweet newSweet = sweetService.addSweet(sweet);
        return ResponseEntity.created(URI.create("/api/sweets/" + newSweet.getId())).body(newSweet);
    }

    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        return ResponseEntity.ok(sweetService.getAllSweets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sweet> getSweetById(@PathVariable Long id) {
        return ResponseEntity.ok(sweetService.getSweetById(id));
    }

}
