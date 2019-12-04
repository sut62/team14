package com.cpe.manage.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="Disease")
public class Disease {

    @Id
    @SequenceGenerator(name="Disease_SEQ",sequenceName="Disease_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Disease_SEQ")
    @Column(name="Disease_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String dis_name;

    @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "setage"
    private Collection<Register> man;
}
