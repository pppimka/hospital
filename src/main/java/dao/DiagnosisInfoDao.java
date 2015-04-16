package dao;

import model.DiagnosisInfoEntity;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by ann_ on 16.02.15.
 */
public interface DiagnosisInfoDao {



    public DiagnosisInfoEntity create(ResultSet rs);

    public DiagnosisInfoEntity getByPK(int key);

    public boolean update(DiagnosisInfoEntity object);

    public void delete(DiagnosisInfoEntity object);

    public List<DiagnosisInfoEntity> getAll();
}
