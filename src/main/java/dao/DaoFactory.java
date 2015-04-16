package dao;

/**
 * Created by ann_ on 31.01.15.
 */
public interface DaoFactory {

    public PatientDao getPatientDao();

    public StaffDao getStaffDao();

    public PatientDiagnosisDao getPatientDiagnosisDao();

    public DiagnosisInfoDao getDiagnosisInfoDao();

    public PrescriptionDao getPrescriptionDao();

    public ReceptionDao getReceptionDao();
}
