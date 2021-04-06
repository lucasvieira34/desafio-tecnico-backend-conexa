package br.com.conexasaude.desafiotecnicobackendconexa.application.service.impl;

import br.com.conexasaude.desafiotecnicobackendconexa.application.model.DoctorModel;
import br.com.conexasaude.desafiotecnicobackendconexa.application.service.DoctorService;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.Doctor;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DoctorModel> getDoctors() {
        List<Doctor> doctors = repository.findAll();

        List<DoctorModel> doctorModels = doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorModel.class))
                                                  .collect(Collectors.toList());
        return doctorModels;
    }

}
