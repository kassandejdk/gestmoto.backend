package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmEmploye;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GmEmployeRepository extends JpaRepository<GmEmploye, String> {
    /**
     * Get a list of employees.
     * @param eStatut
     * @return list of employees
     */
    List<GmEmploye> findByStatut(EStatut eStatut);
}
