package com.example.assignment.controller;

import com.example.assignment.Record;
import com.example.assignment.Category;
import com.example.assignment.repository.RecordRepository;
import com.example.assignment.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RecordController {

    @Autowired
    private RecordRepository recordRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // Get all or filtered records
    @GetMapping("/records")
    public List<Record> getAllRecords(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String name) {

        if (active != null && name != null && !name.isBlank()) {
            return recordRepo.findByActiveAndNameContainingIgnoreCase(active, name);
        } else if (active != null) {
            return recordRepo.findByActive(active);
        } else if (name != null && !name.isBlank()) {
            return recordRepo.findByNameContainingIgnoreCase(name);
        } else {
            return recordRepo.findAll();
        }
    }


    // Get single record by ID
    @GetMapping("/records/{id}")
    public Record getRecordById(@PathVariable int id) {
        return recordRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }

    // Create a new record
    @PostMapping("/records")
    public Record createRecord(@RequestBody Record record) {
        return recordRepo.save(record);
    }

    // Update a record
    @PutMapping("/records/{id}")
    public Record updateRecord(@PathVariable int id, @RequestBody Record record) {
        record.setId(id);
        return recordRepo.save(record);
    }

    // Delete a single record
    @DeleteMapping("/records/{id}")
    public void deleteRecord(@PathVariable int id) {
        recordRepo.deleteById(id);
    }

    // Bulk delete
    @PostMapping("/records/delete-bulk")
    public void deleteBulk(@RequestBody List<Integer> ids) {
        ids.forEach(recordRepo::deleteById);
    }

    // Get all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
