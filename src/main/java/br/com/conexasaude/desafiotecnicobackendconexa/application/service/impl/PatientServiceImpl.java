package br.com.conexasaude.desafiotecnicobackendconexa.application.service.impl;

import br.com.conexasaude.desafiotecnicobackendconexa.application.model.PatientModel;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.PatientService;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Patient;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Patient createPatient(PatientModel patientModel) {
        Patient patient = modelMapper.map(patientModel, Patient.class);
        return repository.save(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return repository.findById(id);
    }
}
