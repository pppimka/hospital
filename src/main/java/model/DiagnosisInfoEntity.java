package model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ann_ on 12.02.15.
 */
@Entity
@Table(name = "diagnosis_info", schema = "", catalog = "hospital")
public class DiagnosisInfoEntity implements Serializable {
    public static final Logger logger = Logger.getLogger(DiagnosisInfoEntity.class);

    private Integer idDiagnosis;
    private String name;
    private String description;

    @Id
    @Column(name = "ID_Diagnosis")
    public Integer getIdDiagnosis() {
        logger.debug(idDiagnosis);
        return idDiagnosis;
    }

    public void setIdDiagnosis(Integer idDiagnosis) {
        logger.debug(idDiagnosis);
        this.idDiagnosis = idDiagnosis;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        logger.debug(name);
        return name;
    }

    public void setName(String name) {
        logger.debug(name);
        this.name = name;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        logger.debug(description);
        return description;
    }

    public void setDescription(String description) {
        logger.debug(description);
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiagnosisInfoEntity that = (DiagnosisInfoEntity) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (idDiagnosis != null ? !idDiagnosis.equals(that.idDiagnosis) : that.idDiagnosis != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDiagnosis != null ? idDiagnosis.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
