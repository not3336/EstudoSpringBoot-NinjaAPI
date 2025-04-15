package com.rafael.NinjaAPI.ninjas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    public Optional<NinjaModel> findByEmail(String email);
}
