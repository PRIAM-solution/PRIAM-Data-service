package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import priam.data.priamdataservice.enums.ProcessingCategory;
import priam.data.priamdataservice.enums.ProcessingType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gdpr_Processing")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Processing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private ProcessingType type;

    private ProcessingCategory category;

   /* private int dataId;
    @Transient
    private Data data;*/

    @Column(nullable = true,updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(nullable = true)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatingDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processing",fetch = FetchType.EAGER)
    private List<DataUsage> dataUsages;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Purpose> purposes;

    public Processing(String name, ProcessingType type, ProcessingCategory category, Date creationDate,
                      Date updatingDate, List<DataUsage> dataUsages, List<Purpose> purposes, List<Mesure> mesures) {
        super();
        this.name = name;
        this.type = type;
        this.category = category;
        this.creationDate = creationDate;
        this.updatingDate = updatingDate;
        this.dataUsages = dataUsages;
        this.purposes = purposes;
        this.mesures = mesures;
    }

    @ManyToMany
    @JoinTable(name = "gdpr_ProcessingMesure", joinColumns = @JoinColumn(name = "processingID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "mesureID", referencedColumnName = "mesureID"))
    private List<Mesure> mesures;
}
