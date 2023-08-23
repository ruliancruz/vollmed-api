package med.voll.api.model.patient;

public record PatientGetData(Long id, String nome, String email, String cpf)
{
    public PatientGetData(Patient patient)
    {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}