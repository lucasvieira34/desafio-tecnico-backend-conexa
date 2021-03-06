package br.com.conexasaude.desafiotecnicobackendconexa.domain.repository;

import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}

