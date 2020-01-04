package com.cpe.patientBed.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.cpe.personnel.entity.Personnel;
//import cause out of my package u nok package rao
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name = "PATIENTBED")
public class PatientBed {

    @Id
    @SequenceGenerator(name = "patientbed_seq",sequenceName = "patientbed_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patientbed_seq")


    @Column(name = "PATIENTBED_ID",unique = true,nullable = true)
    private @NonNull Long patientBed_id;
  

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Personnel.class)
    @JoinColumn(name = "PERSONNEL_ID", insertable = true)
    private Personnel createdBy; //is in Personnel



   @ManyToOne(fetch = FetchType.EAGER,targetEntity = PatientZone.class)
   @JoinColumn(name = "ZONE_ID",insertable = true)
   private  PatientZone atZone; //is in PatientZone 
    

   @ManyToOne(fetch = FetchType.EAGER, targetEntity = PatientRoom.class)
   @JoinColumn(name = "ROOM_ID", insertable = true)
    private PatientRoom atRoom; 

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PhysicalBed.class)
    @JoinColumn(name = "PHYSICALBED_ID", insertable = true)
    private PhysicalBed physicalBed; 

    private @NonNull String detail;

}