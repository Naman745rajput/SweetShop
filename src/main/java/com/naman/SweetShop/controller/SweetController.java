package com.naman.SweetShop.controller;

import com.naman.SweetShop.model.Sweet;
import com.naman.SweetShop.service.SweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.naman.SweetShop.repo.SweetRepository;

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
    public ResponseEntity<Sweet> addSweet(@RequestBody Sweet sweet) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSweet(@PathVariable Long id) {
        sweetService.deleteSweet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sweet> updateSweet(@PathVariable Long id, @RequestBody Sweet sweetDetails) {
        Sweet updatedSweet = sweetService.updateSweet(id, sweetDetails);
        return ResponseEntity.ok(updatedSweet);
    }

    @GetMapping("/search")
    public List<Sweet> searchSweets(@RequestParam String keyword) {
        return sweetService.searchSweets(keyword);
    }

}
