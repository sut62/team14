package com.cpe.DiseasePackage.entity;

import lombok.*;
import com.cpe.DiseasePackage.entity.Disease;
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
@Table(name="LIFESPAN")
public class LifeSpan {
	
	@Id
	@SequenceGenerator(name="lifespan_seq",sequenceName="lifespan_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="lifespan_seq")
	
	@Column(name="LIFESPAN_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String age;

	public void setAge(String age){
		this.age = age;
	}
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Disease> disease ;

	public void setLifeSpan(String string) {
	}
}