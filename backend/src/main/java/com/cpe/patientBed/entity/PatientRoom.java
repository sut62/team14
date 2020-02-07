package com.cpe.patientBed.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Data
@Entity
@NoArgsConstructor
@Table(name="PATIENTROOM")
public class PatientRoom {
	@Id
	@SequenceGenerator(name="room_seq",sequenceName="room_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="room_seq")
	@Column(name="ROOM_ID",unique = true, nullable = true)
	private @NonNull Long room_id;
	private @NotNull String room_name;

	@OneToMany(fetch = FetchType.EAGER)
	// mappedBy  = "atRoom"
	private Collection<PatientBed> patientBedRoom; //map from PatientBed - patientBedRoom is in PatientBed

	public void setName(String room_name){
		this.room_name=room_name;
	
	}
}
