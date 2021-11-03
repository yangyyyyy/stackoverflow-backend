package com.group8.backend.Repository;

import com.group8.backend.PO.EdgeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EdgeRepository extends CrudRepository<EdgeEntity,Integer> {
    @Query("select n from EdgeEntity n where n.chart_id = :chart_id and n.edge_name= :edge_name")
    List<EdgeEntity> findByEdge_Name(@Param("chart_id") int chart_id, @Param("edge_name") String edge_name);

    @Query("select n from EdgeEntity n where n.chart_id = :chart_id")
    List<EdgeEntity> findByChart_id(@Param("chart_id") int chart_id);
}
