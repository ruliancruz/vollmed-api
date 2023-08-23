package med.voll.api.model.doctor;

public record DoctorGetData(Long id, String name, String email, String crm, Specialization specialization)
{
    public DoctorGetData(Doctor doctor)
    {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialization());
    }
}
