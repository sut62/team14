package com.cpe.personnel.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="MARITALSTATUS")
public class Maritalstatus {

    @Id
    @SequenceGenerator(name="MARITALSTATUS_SEQ",sequenceName="MARITALSTATUS_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MARITALSTATUS_SEQ")  
    @Column(name="MARITALSTATUS_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NotNull String maritalstatus_name;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Personnel> personnel;
}
