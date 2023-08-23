package med.voll.api.model.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.infrastructure.utility.StringValidation;
import med.voll.api.model.address.Address;

@Entity(name = "Doctor")
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Embedded
    private Address address;

    private boolean active;

    public Doctor(DoctorPostData doctorPostData) {
        this.active = true;
        this.name = doctorPostData.name();
        this.email = doctorPostData.email();
        this.phone = doctorPostData.phone();
        this.crm = doctorPostData.crm();
        this.specialization = doctorPostData.specialization();
        this.address = new Address(doctorPostData.address());
    }

    public void dataUpdate(DoctorPutData doctorPutData)
    {
        if(!StringValidation.isNullEmptyOrBlank(doctorPutData.name()))
            this.name = doctorPutData.name();

        if(!StringValidation.isNullEmptyOrBlank(doctorPutData.phone()))
            this.phone = doctorPutData.phone();

        if(doctorPutData.address() != null)
            this.address.dataUpdate(doctorPutData.address());
    }

    public void delete()
    {
        this.active = false;
    }
}