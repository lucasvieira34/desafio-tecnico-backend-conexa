package br.com.conexasaude.desafiotecnicobackendconexa.application.model;

import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleModel {

    @JsonIgnore
    private Patient patient;

    @JsonProperty("id_paciente")
    private Long idPaciente;

    @JsonProperty("data_hora_atendimento")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAtendimento;

    public Long getIdPaciente() {
        return patient.getId();
    }
}
