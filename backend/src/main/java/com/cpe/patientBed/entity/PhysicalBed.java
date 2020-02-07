package com.cpe.patientBed.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@Data
@Entity
@NoArgsConstructor
@Table(name="PHYSICALBED")
public class PhysicalBed {

    @Id
    @SequenceGenerator(name="phys_seq",sequenceName="phys_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="phys_seq")  
    @Column(name = "PHYSICALBED_ID", unique = true, nullable = true)
    private @NonNull Long physBed_id;
    private @NotNull String physBed_name;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "physicalBed"
    private Collection<PatientBed> havePhysical; //it map from PatientBed - havePhysical is in PatientBed

   public void setName(String physBed_name){
		this.physBed_name=physBed_name;
		
	}
}
