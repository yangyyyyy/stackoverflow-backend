package com.group8.backend.Repository;

import com.group8.backend.PO.NodeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NodeRepository extends CrudRepository<NodeEntity,Integer> {
    @Query("select n from NodeEntity n where n.chart_id = :chart_id and n.node_name= :node_name")
    List<NodeEntity> findByNode_Name(@Param("chart_id") int chart_id, @Param("node_name") String node_name);

    @Query("select n from NodeEntity n where n.chart_id = :chart_id")
    List<NodeEntity> findByChart_id(@Param("chart_id") int chart_id);
}
