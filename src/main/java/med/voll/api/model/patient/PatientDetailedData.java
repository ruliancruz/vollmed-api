package med.voll.api.model.patient;

import med.voll.api.model.address.Address;

public record PatientDetailedData(Long id, String name, String email, String phone, String cpf, Address address)
{
    public PatientDetailedData(Patient patient)
    {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}
