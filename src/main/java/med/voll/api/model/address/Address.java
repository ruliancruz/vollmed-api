package med.voll.api.model.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.infrastructure.utility.StringValidation;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address
{
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String uf;
    private String cep;

    public Address(AddressGetData addressGetData)
    {
        this.street = addressGetData.street();
        this.number = addressGetData.number();
        this.complement = addressGetData.complement();
        this.district = addressGetData.district();
        this.city = addressGetData.city();
        this.uf = addressGetData.uf();
        this.cep = addressGetData.cep();
    }

    public void dataUpdate(AddressPutData addressPutData)
    {

        if(!StringValidation.isNullEmptyOrBlank(addressPutData.street()))
            this.street = addressPutData.street();

        if(!StringValidation.isNullEmptyOrBlank(addressPutData.number()))
            this.number = addressPutData.number();

        if(!StringValidation.isNullEmptyOrBlank(addressPutData.complement()))
            this.complement = addressPutData.complement();

        if(!StringValidation.isNullEmptyOrBlank(addressPutData.district()))
            this.district = addressPutData.district();

        if(!StringValidation.isNullEmptyOrBlank(addressPutData.city()))
            this.city = addressPutData.city();

        if(!StringValidation.isNullEmptyOrBlank(addressPutData.uf()))
            this.uf = addressPutData.uf();

        if(!StringValidation.isNullEmptyOrBlank(addressPutData.cep()))
            this.cep = addressPutData.cep();
    }
}