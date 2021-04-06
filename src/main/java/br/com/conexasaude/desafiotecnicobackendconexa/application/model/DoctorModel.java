package br.com.conexasaude.desafiotecnicobackendconexa.application.model;

import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Doctor;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorModel {

    @JsonProperty("token")
    private String token;
    @JsonProperty("medico")
    private String nome;
    @JsonProperty("especialidade")
    private String especialidade;
    @JsonProperty("agendamentos_hoje")
    private List<ScheduleModel> agendamentos;

}
