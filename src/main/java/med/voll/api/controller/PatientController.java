package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.patient.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController
{
    private final PatientRepository repository;

    public PatientController(PatientRepository repository)
    {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity postPatient(@RequestBody @Valid PatientPostData patientPostData, UriComponentsBuilder uriComponentsBuilder)
    {
        Patient patient = new Patient(patientPostData);
        repository.save(patient);
        return ResponseEntity.created(uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri()).body(new PatientDetailedData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientGetData>> getPatient(@PageableDefault(sort = {"name"}) Pageable pagination)
    {
        return ResponseEntity.ok(repository.findAllByActiveTrue(pagination).map(PatientGetData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity putPatient(@RequestBody @Valid PatientPutData patientPutData)
    {
        Patient patient = repository.getReferenceById(patientPutData.id());
        patient.dataUpdate(patientPutData);
        return ResponseEntity.ok(new PatientDetailedData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id)
    {
        repository.getReferenceById(id).delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getDetailedDoctor(@PathVariable Long id)
    {
        Patient patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailedData(patient));
    }
}