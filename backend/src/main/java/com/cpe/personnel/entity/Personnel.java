package com.cpe.personnel.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Collection;
import com.cpe.personnel.entity.Personnel;
import com.cpe.orderfood.entity.FoodOrder;
import com.cpe.patientBed.entity.PatientBed;
import com.cpe.backendfood.entity.Food;
import com.cpe.DiseasePackage.entity.Disease;
import com.cpe.register.entity.Register;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@Entity
@NoArgsConstructor
@Table(name="PERSONNEL")
public class Personnel {

    @Id
    @SequenceGenerator(name="PERSONNEL_seq",sequenceName="PERSONNEL_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PERSONNEL_seq")
    @Column(name = "PERSONNEL_ID", unique = true, nullable = true)
    private @NonNull Long id;
    @Pattern(regexp = "[a-zA-Z[ก-ฮ]*]*")
    @Size(min = 2 ,max = 100)
    private @NotNull String firstname;
    @NotNull
    @Pattern(regexp = "[a-zA-Z[ก-ฮ]*]*")
    @Size(min = 2 ,max = 100)
    private  String lastname;
    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String telephone;
    @NotNull
    @Past
    private LocalDate Birthday;
    @NotNull
    @Size(min = 2 ,max = 100)
    private  String password;
    

   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "POSITION_ID", insertable = true)
    
    private @NotNull Position posit;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Maritalstatus.class)
    @JoinColumn(name = "MARITALSTATUS_ID", insertable = true)
    
    private @NotNull Maritalstatus status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Educationlevel.class)
    @JoinColumn(name = "EDUCATIONLEVEL_ID", insertable = true)
    
    private @NotNull Educationlevel level;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<FoodOrder> order;

    @OneToMany(fetch = FetchType.EAGER)
	private Collection<Disease> disease;
    
    @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "createdBy"
    private Collection<PatientBed> add;

    @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "createdBy"
    private Collection<Food> food;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Register> regis;

}