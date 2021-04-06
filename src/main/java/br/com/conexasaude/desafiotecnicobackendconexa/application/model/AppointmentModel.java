package br.com.conexasaude.desafiotecnicobackendconexa.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentModel {

    @JsonProperty("data_hora_atendimento")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraAtendimento;
    @JsonProperty("id_paciente")
    private Long idPaciente;

}
