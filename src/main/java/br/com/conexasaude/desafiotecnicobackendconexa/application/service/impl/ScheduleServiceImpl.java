package br.com.conexasaude.desafiotecnicobackendconexa.application.service.impl;

import br.com.conexasaude.desafiotecnicobackendconexa.application.model.AppointmentModel;
import br.com.conexasaude.desafiotecnicobackendconexa.application.model.DoctorModel;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.PatientService;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.ScheduleService;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Doctor;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Patient;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Schedule;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.User;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.repository.ScheduleRepository;
import br.com.conexasaude.desafiotecnicobackendconexa.infrastructure.configuration.jwt.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    private User userLogged;

    @Autowired
    private ScheduleRepository repository;

    @Override
    public DoctorModel getSchedulesForDoctors(String token) {
        userLogged();
        Doctor doctor = userLogged.getDoctor();
        token = token.replace("Bearer ", "");
        DoctorModel doctorModel = modelMapper.map(doctor, DoctorModel.class);
        doctorModel.setToken(token);

        return doctorModel;
    }

    @Override
    public AppointmentModel makeAppointment(AppointmentModel appointmentModel) {
        userLogged();
        Doctor doctor = userLogged.getDoctor();
        Patient patient = patientService.findById(appointmentModel.getIdPaciente()).orElseThrow(() -> new InvalidDataAccessApiUsageException("Patient not found."));
        Schedule schedule = new Schedule();
        schedule.setDoctor(doctor);
        schedule.setPatient(patient);
        schedule.setDataAtendimento(appointmentModel.getDataHoraAtendimento());
        repository.save(schedule);
        return appointmentModel;
    }

    private void userLogged() {
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        if (!(authenticated instanceof AnonymousAuthenticationToken)) {
            String username = authenticated.getName();
            userLogged = userService.findByUsername(username).get();
        }
    }

}
