package med.voll.api.model.patient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import med.voll.api.model.address.AddressPutData;

public record PatientPutData
(
    @NotNull
    Long id,

    @Size(max = 100, message = "{validation.name.size.too_long}")
    String name,

    @Pattern(regexp = "\\d{8,13}")
    String phone,

    AddressPutData address
){}