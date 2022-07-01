package com.task7.task7.repository;

import com.task7.task7.domain.ModelAuto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelAutoRepository extends CrudRepository<ModelAuto, Long> {

    @Query(nativeQuery = true, value = "select * from model_auto " +
            "join modification " +
            "on model_auto.id = modification.model_id " +
            "where model_auto.name = :nameModel " +
            "and modification.period_begin >= :periodBegin " +
            "and modification.period_end <= :periodEnd ")
    List<ModelAuto> findByNameModel(@Param("nameModel") String nameModel, @Param("periodBegin") int periodBegin, @Param("periodEnd") int periodEnd);
}
