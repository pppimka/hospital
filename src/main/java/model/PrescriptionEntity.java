package model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ann_ on 12.02.15.
 */
@Entity
@Table(name = "prescription", schema = "", catalog = "hospital")
public class PrescriptionEntity implements Serializable{

    public static final Logger logger = Logger.getLogger(PatientInfoEntity.class);

    private Integer idPrescription;
    private String drugs;
    private String procedure;
    private String operation;

    @Id
    @Column(name = "ID_Prescription")
    public Integer getIdPrescription() {
        logger.debug(idPrescription);
        return idPrescription;
    }

    public void setIdPrescription(Integer idPrescription) {
        logger.debug(idPrescription);
        this.idPrescription = idPrescription;
    }

    @Basic
    @Column(name = "Drugs")
    public String getDrugs() {
        logger.debug(drugs);
        return drugs;
    }

    public void setDrugs(String drugs) {
        logger.debug(drugs);
        this.drugs = drugs;
    }

    @Basic
    @Column(name = "Procedure")
    public String getProcedure() {
        logger.debug(procedure);
        return procedure;
    }

    public void setProcedure(String procedure) {
        logger.debug(procedure);
        this.procedure = procedure;
    }

    @Basic
    @Column(name = "Operation")
    public String getOperation() {
        logger.debug(operation);
        return operation;
    }

    public void setOperation(String operation) {
        logger.debug(operation);
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrescriptionEntity that = (PrescriptionEntity) o;

        if (drugs != null ? !drugs.equals(that.drugs) : that.drugs != null) return false;
        if (idPrescription != null ? !idPrescription.equals(that.idPrescription) : that.idPrescription != null)
            return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;
        if (procedure != null ? !procedure.equals(that.procedure) : that.procedure != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPrescription != null ? idPrescription.hashCode() : 0;
        result = 31 * result + (drugs != null ? drugs.hashCode() : 0);
        result = 31 * result + (procedure != null ? procedure.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        return result;
    }

}
