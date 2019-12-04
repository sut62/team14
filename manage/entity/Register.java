package com.cpe.manage.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.cpe.manage.entity.Register;

@Data
@Entity
@NoArgsConstructor
@Table(name="Register")
public class Register {

    @Id
    @SequenceGenerator(name="Register_seq",sequenceName="Register_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Register_seq")
    @Column(name = "Register_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String addlastname;
    private @NonNull String addname;
    private @NonNull String addage;
    private @NonNull String telephone;
   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private Gender gendernname;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "Disease_ID", insertable = true)
    private Disease diseasename;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bloodtype.class)
    @JoinColumn(name = "Bloodtype_ID", insertable = true)
    private Bloodtype bloodtypename;
}