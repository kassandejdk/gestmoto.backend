package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GmUserRepository extends JpaRepository<GmUser, String> {
}
