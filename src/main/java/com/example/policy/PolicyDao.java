package com.example.policy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyDao extends JpaRepository<Policy, Integer> {

    List<Policy> findByCustomerId(Integer customer_id);
}

