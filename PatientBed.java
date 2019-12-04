package lab3Entity.Entity;
import lombok.*;

import javax.persistence.Id;


import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name = "PatientBed")
public class PatientBed{

        @Id
        @SequenceGenerator(name = "bed_seq",sequenceName = "bed_seq")
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bed_seq")
        @Column(name = "BED_ID",unique = true,nullable = true)
        private @NonNull Long id;
        private @NonNull String detail;

}