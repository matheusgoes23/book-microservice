package com.matheusgoes23.cambio.service.repository;

import com.matheusgoes23.cambio.service.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Cambio findByFromAndTo(String from, String to);
}
