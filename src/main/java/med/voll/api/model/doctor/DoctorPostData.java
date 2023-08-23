package med.voll.api.model.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.model.address.AddressGetData;

public record DoctorPostData
(
    @NotBlank
    @Size(max = 100, message = "{validation.name.size.too_long}")
    String name,

    @NotBlank
    @Email
    @Size(max = 100, message = "{validation.email.size.too_long}")
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{8,13}")
    String phone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm,

    @NotNull
    Specialization specialization,

    @NotNull
    @Valid
    AddressGetData address
){}