package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.PersonaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends CrudRepository<PersonaEntity, Long> {
}
