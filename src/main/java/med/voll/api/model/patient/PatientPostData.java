package med.voll.api.model.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.model.address.AddressGetData;
import org.hibernate.validator.constraints.br.CPF;

public record PatientPostData
(
    @NotBlank
    @Size(max = 100, message = "{validation.name.size.too_long}")
    String name,

    @NotBlank
    @Email
    @Size(max = 100, message = "{validation.name.size.too_long}")
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{8,13}")
    String phone,

    @NotBlank
    @CPF
    @Pattern(regexp = "\\d{11}")
    String cpf,

    @NotNull
    @Valid
    AddressGetData address
){}