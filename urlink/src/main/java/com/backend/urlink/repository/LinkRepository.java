package com.backend.urlink.repository;

import com.backend.urlink.models.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Links, Long> {
}
