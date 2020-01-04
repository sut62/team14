package com.cpe.personnel.entity;
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

@Data
@Entity
@NoArgsConstructor
@Table(name="POSITION")
public class Position {

    @Id
    @SequenceGenerator(name="POSITION_SEQ",sequenceName="POSITION_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="POSITION_SEQ")
    @Column(name="POSITION_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String position_name;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Personnel> personnel;
}
