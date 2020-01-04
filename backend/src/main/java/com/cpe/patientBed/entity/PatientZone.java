package com.cpe.patientBed.entity;

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

@Setter
@Getter
@Data
@Entity
@NoArgsConstructor
@Table(name="PATIENTZONE")
public class PatientZone {
    @Id
    @SequenceGenerator(name="ZONE_SEQ",sequenceName="ZONE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZONE_SEQ")
    @Column(name="ZONE_ID",unique = true, nullable = true)
    private @NonNull Long zone_id;
    private @NonNull String zone_name;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "atZone"
    private Collection<PatientBed> patientBedZone;//map from PatientBed - patientBedZone is in PatientBed

    public String setName(String zone_name){
      this.zone_name=zone_name;
      return zone_name;
    }
}