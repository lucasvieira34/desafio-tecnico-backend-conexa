package br.com.conexasaude.desafiotecnicobackendconexa.application.service;

import br.com.conexasaude.desafiotecnicobackendconexa.application.model.PatientModel;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Patient;

import java.util.Optional;

public interface PatientService {

    Patient createPatient(PatientModel patientModel);
    Optional<Patient> findById(Long id);

}
