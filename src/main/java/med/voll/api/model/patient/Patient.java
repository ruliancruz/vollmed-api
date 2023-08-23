package med.voll.api.model.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.infrastructure.utility.StringValidation;
import med.voll.api.model.address.Address;

@Entity(name = "Patient")
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;

    @Embedded
    private Address address;

    private boolean active;

    public Patient(PatientPostData patientPostData)
    {
        this.active = true;
        this.name = patientPostData.name();
        this.email = patientPostData.email();
        this.phone = patientPostData.phone();
        this.cpf = patientPostData.phone();
        this.address = new Address(patientPostData.address());
    }

    public void dataUpdate(PatientPutData patientPutData)
    {
        if(!StringValidation.isNullEmptyOrBlank(patientPutData.name()))
            this.name = patientPutData.name();

        if(!StringValidation.isNullEmptyOrBlank(patientPutData.phone()))
            this.phone = patientPutData.phone();

        if(patientPutData.address() != null)
            this.address.dataUpdate(patientPutData.address());
    }

    public void delete()
    {
        this.active = false;
    }
}