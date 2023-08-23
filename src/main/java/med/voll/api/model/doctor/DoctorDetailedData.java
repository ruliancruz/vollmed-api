package med.voll.api.model.doctor;

import med.voll.api.model.address.Address;

public record DoctorDetailedData(Long id, String name, String email, String phone, String crm, Specialization specialization, Address address)
{
    public DoctorDetailedData(Doctor doctor)
    {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialization(), doctor.getAddress());
    }
}