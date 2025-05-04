package com.example.demo.service.implementation;

import com.example.demo.persistence.dao.interfaces.IPersonaDAO;
import com.example.demo.persistence.entity.PersonaEntity;
import com.example.demo.presentation.dto.PersonaDTO;
import com.example.demo.service.interfaces.IPersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaDAO userDAO;

    @Override
    public List<PersonaDTO> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        return this.userDAO.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, PersonaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonaDTO findById(Long id) {

        Optional<PersonaEntity> userEntity = this.userDAO.findById(id);

        if (userEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            PersonaEntity currentUser = userEntity.get();
            return modelMapper.map(currentUser,PersonaDTO.class);
        } else {
            return new PersonaDTO();
        }
    }

    @Override
    public PersonaDTO createUser(PersonaDTO userDTO) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            PersonaEntity userEntity = modelMapper.map(userDTO, PersonaEntity.class);
            this.userDAO.saveUser(userEntity);
            return userDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }


    @Override
    public PersonaDTO updateUser(PersonaDTO userDTO, Long id) {

        Optional<PersonaEntity> userEntity = this.userDAO.findById(id);

        if (userEntity.isPresent()) {
            PersonaEntity currentUserEntity = userEntity.get();
            currentUserEntity.setNom(userDTO.getNom());
            currentUserEntity.setCognom1(userDTO.getCognom1());
            currentUserEntity.setCognom2(userDTO.getCognom2());
            currentUserEntity.setDataNaix(userDTO.getDataNaix());
            currentUserEntity.setTelefon(userDTO.getTelefon());
            currentUserEntity.setMail(userDTO.getMail());

            this.userDAO.updateUser(currentUserEntity);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentUserEntity, PersonaDTO.class);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }



    @Override
    public String deleteUser(Long id) {

        Optional<PersonaEntity> userEntity = this.userDAO.findById(id);

        if (userEntity.isPresent()) {
            PersonaEntity currentUserEntity = userEntity.get();
            this.userDAO.deleteUser(currentUserEntity);
            return "Usuario con ID " + id + " eliminado correctamente";
        } else {
            return "El usuario con ID " + id + " no existe";
        }
    }
}
