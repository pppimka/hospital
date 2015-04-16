package dao;

import model.PatientInfoEntity;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by ann_ on 16.02.15.
 */
public interface PatientDao {

    public boolean addNewPatient(PatientInfoEntity patient);

    public PatientInfoEntity create(ResultSet rs);

    public PatientInfoEntity getByPK(int key);

    public boolean checkEmail(String email);

    public PatientInfoEntity login(String email, String password);

    public boolean update(PatientInfoEntity object);

    public void delete(PatientInfoEntity object);

    public List<PatientInfoEntity> getAll();
}
