package com.example.assignment.repository;

import com.example.assignment.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findByActive(boolean active);
    List<Record> findByNameContainingIgnoreCase(String name);
    List<Record> findByActiveAndNameContainingIgnoreCase(boolean active, String name);
}