package com.cpe.register.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.cpe.register.entity.Register;
import com.cpe.orderfood.entity.FoodOrder;
import com.cpe.personnel.entity.Personnel;
import com.cpe.DiseasePackage.entity.Disease;
import com.cpe.patientBed.entity.PatientBed;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;

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
    @NotNull
    private  String addlastname;
    @NotNull
    private  String addname;
    @NotNull
    @Max(200)
    private Integer addage;
    @Pattern(regexp = "\\d{10}")
    private @NonNull String telephone;
   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private Gender gendername;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bloodtype.class)
    @JoinColumn(name = "Bloodtype_ID", insertable = true)
    private Bloodtype bloodtypename;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Personnel.class)
    @JoinColumn(name = "PERSONNEL_ID", insertable = true)
    private Personnel createdby;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "DISEASE_ID", insertable = true)
    private Disease desname;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PatientBed.class)
    @JoinColumn(name = "PatientBed_ID", insertable = true)
    private PatientBed patname;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<FoodOrder> order;
}