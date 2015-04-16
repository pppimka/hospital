package dao;

import model.PatientsDiagnosisEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by ann_ on 16.02.15.
 */
public interface PatientDiagnosisDao {
    public PatientsDiagnosisEntity create(ResultSet rs);

    public List<PatientsDiagnosisEntity> getByIdPatient(int key);

    public List<PatientsDiagnosisEntity> getByIdDoctor(int key);

    public List<PatientsDiagnosisEntity> getForNurse();

    public boolean update(PatientsDiagnosisEntity object);

    public boolean add(PatientsDiagnosisEntity object, Connection connection);

    public void delete(PatientsDiagnosisEntity object, Connection connection);

    public List<PatientsDiagnosisEntity> getAll();
}
