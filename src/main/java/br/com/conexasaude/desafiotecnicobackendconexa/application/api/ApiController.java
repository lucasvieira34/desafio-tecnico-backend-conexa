package br.com.conexasaude.desafiotecnicobackendconexa.application.api;

import br.com.conexasaude.desafiotecnicobackendconexa.application.model.*;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.DoctorService;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.PatientService;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.ScheduleService;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.impl.JwtUserDetailsService;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Patient;
import br.com.conexasaude.desafiotecnicobackendconexa.infrastructure.configuration.jwt.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/conexa-saude")
@CrossOrigin
public class ApiController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthModel authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logout(@RequestBody LogoutModel logoutModel) {
        jwtTokenUtil.expireToken(logoutModel.getToken());
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getScheduleForDoctor(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(scheduleService.getSchedulesForDoctors(token));
    }

    @PostMapping(value = "/patients")
    public ResponseEntity createPatient(@RequestBody PatientModel patientModel) {
        Patient patient = patientService.createPatient(patientModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/appointment")
    public ResponseEntity makeAppointment(@RequestBody AppointmentModel appointmentModel) {
        return ResponseEntity.ok(scheduleService.makeAppointment(appointmentModel));
    }

}
