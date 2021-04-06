package br.com.conexasaude.desafiotecnicobackendconexa.application.service;

import br.com.conexasaude.desafiotecnicobackendconexa.application.model.AppointmentModel;
import br.com.conexasaude.desafiotecnicobackendconexa.application.model.DoctorModel;

public interface ScheduleService {

    DoctorModel getSchedulesForDoctors(String token);
    AppointmentModel makeAppointment(AppointmentModel appointmentModel);
}
