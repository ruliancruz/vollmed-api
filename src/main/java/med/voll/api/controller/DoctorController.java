package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.doctor.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
public class DoctorController
{
    final DoctorRepository repository;

    public DoctorController(DoctorRepository repository)
    {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity postDoctor(@RequestBody @Valid DoctorPostData doctorPostData, UriComponentsBuilder uriComponentsBuilder)
    {
        Doctor doctor = new Doctor(doctorPostData);
        repository.save(doctor);
        return ResponseEntity.created(uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri()).body(new DoctorDetailedData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorGetData>> getDoctors(@PageableDefault(sort = {"name"}) Pageable pagination)
    {
        return ResponseEntity.ok(repository.findAllByActiveTrue(pagination).map(DoctorGetData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity putDoctor(@RequestBody @Valid DoctorPutData doctorPutData)
    {
        Doctor doctor = repository.getReferenceById(doctorPutData.id());
        doctor.dataUpdate(doctorPutData);
        return ResponseEntity.ok(new DoctorDetailedData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id)
    {
        repository.getReferenceById(id).delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getDetailedDoctor(@PathVariable Long id)
    {
        if(repository.getReferenceById(id).isActive())
        {
            return ResponseEntity.ok(new DoctorDetailedData(repository.getReferenceById(id)));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}