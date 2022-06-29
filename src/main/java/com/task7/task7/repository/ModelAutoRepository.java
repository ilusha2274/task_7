package com.task7.task7.repository;

import com.task7.task7.domain.ModelAuto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelAutoRepository extends CrudRepository<ModelAuto, Long> {
}
