package br.com.conexasaude.desafiotecnicobackendconexa.domain.repository;

import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
