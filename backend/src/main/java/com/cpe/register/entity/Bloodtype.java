package com.cpe.register.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="Bloodtype")
public class Bloodtype{
    @Id
    @SequenceGenerator(name="Bloodtype_SEQ",sequenceName="Bloodtype_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Bloodtype_SEQ")
    @Column(name="Bloodtype_ID",unique = true, nullable = true)
    private @NonNull Long id;
    @NotNull private  String blood_name;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Register> regis;
}