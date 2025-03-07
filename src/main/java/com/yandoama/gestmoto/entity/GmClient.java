package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "gm_client")
public class GmClient {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "profession")
    private String profession;

    @Column(name = "ville")
    private String ville;

    @Column(name = "province")
    private String province;
}
