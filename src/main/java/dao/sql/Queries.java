package dao.sql;

/**
 * Created by ann_ on 02.02.15.
 */
public class Queries {

    public static final String GET_PATIENT_BY_ID =
            "SELECT * FROM hospital.patient_info where ID_Patient = ?";

    public static final String GET_DIAGNOSIS_INFO_BY_ID =
            "SELECT * FROM hospital.diagnosis_info where ID_Diagnosis = ?";

    public static final String GET_ALL_DIAGNOSIS =
            "SELECT * FROM hospital.diagnosis_info";

    public static final String GET_PRESCRIPTION_INFO_BY_ID =
            "SELECT * FROM hospital.prescription where ID_Prescription = ?";

    public static final String GET_ALL_PRESCRIPTIONS =
            "SELECT * FROM hospital.prescription";

    public static final String GET_STAFF_INFO_BY_ID =
            "SELECT * FROM hospital.staff_info where ID_Staff = ?";

//    public static final String UPDATE_PATIENT_INFO_PASSWORD =
//            "UPDATE patient_info SET Password = ? WHERE ID_Patient = ?";

    public static final String GET_PATIENT_BY_EMAIL =
            "SELECT * FROM hospital.patient_info WHERE e_mail = ?";

    public static final String GET_STAFF_BY_EMAIL =
            "SELECT * FROM hospital.staff_info WHERE e_mail = ?";

    public static final String GET_ALL_PATIENT = "SELECT * FROM hospital.patient_info";

    public static final String GET_ALL_PATIENTS_DIAGNOSIS_BY_ID_PATIENT =
            "SELECT * FROM hospital.patients_diagnosis WHERE ID_Patient = ?";

    public static final String GET_ALL_PATIENTS_DIAGNOSIS =
            "SELECT * FROM hospital.patients_diagnosis";

    public static final String GET_ALL_PATIENTS_DIAGNOSIS_BY_DOCTOR_ID =
            "SELECT * FROM hospital.patients_diagnosis WHERE ID_Doctor = ?";

    public static final String GET_PATIENT_DIAGNOSIS_FOR_NURSE =
            "SELECT * from patients_diagnosis where (Current_Prescription = 2 ||\n" +
                                                    "Current_Prescription = 3 ||\n" +
                                                    "Current_Prescription = 5 ||\n" +
                                                    "Current_Prescription = 6 ||\n" +
                                                    "Current_Prescription = 7 ||\n" +
                                                    "Current_Prescription = 8)";
    public static final String UPDATE_PATIENT_DIAGNOSIS =
            "update patients_diagnosis \n" +
                    "set Date = ?,Initial_Prescription = ?, Current_Prescription = ? \n" +
                    "WHERE (ID_Patient = ? && ID_Doctor = ? && ID_Diagnosis = ?) ";

    public static final String ADD_NEW_PATIENT_DIAGNOSIS =
            "insert into patients_diagnosis \n" +
                    "(Date,Initial_Prescription, Current_Prescription,ID_Patient, ID_Doctor, ID_Diagnosis, ID_Registration)\n" +
                    "    values (?,?,?,?,?,?,?)";

    public static final String ADD_NEW_RECEPTION_RECORD =
            "INSERT INTO reception \n" +
                    "(DateOfAdmission, ID_Patient, ID_Doctor) " +
                    "   VALUES (?, ?, ?)";

    public static final String UPDATE_RECEPTION_RECORD =
            "update reception \n" +
                    "set DateOfDischarge = ?, ID_FinalDiagnosis = ? " +
                    "   where ID_Registration = ? ";
    public static final String DELETE_PATIENTS_DIAGNOSIS =
            "DELETE from patients_diagnosis where ID_Registration = ?";

    public static String ADD_NEW_PATIENT =
            "INSERT INTO hospital.patient_info (Name, Surname, Birthday, PhoneNumber, e_mail, Password) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
}

