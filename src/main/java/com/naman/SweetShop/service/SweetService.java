package com.naman.SweetShop.service;


import com.naman.SweetShop.model.Sweet;
import com.naman.SweetShop.repo.SweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SweetService {

    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public Sweet getSweetById(Long id) {
        return sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + id));
    }

    public void deleteSweet(Long id) {
        sweetRepository.deleteById(id);
    }

    public Sweet updateSweet(Long id, Sweet sweetDetails) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + id));
        if (sweetDetails.getName() != null) sweet.setName(sweetDetails.getName());
        if (sweetDetails.getPrice() != null) sweet.setPrice(sweetDetails.getPrice());
        if (sweetDetails.getQuantity() != null) sweet.setQuantity(sweetDetails.getQuantity());
        if (sweetDetails.getImageUrl() != null) sweet.setImageUrl(sweetDetails.getImageUrl());

        if (sweetDetails.getCategory() != null) sweet.setCategory(sweetDetails.getCategory());

        return sweetRepository.save(sweet);
    }

    public List<Sweet> searchSweets(String keyword) {
        return sweetRepository.searchSweets(keyword);
    }
}
