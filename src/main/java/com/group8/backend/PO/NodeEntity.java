package com.group8.backend.PO;

import com.group8.backend.VO.NodeVO;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="t_node")
@NoArgsConstructor
public class NodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="node_name",unique = true)
    private String node_name;
    @Column(name="chart_id")
    private Integer chart_id;
    @Column(name="node_group")
    private String node_group;
    @Column(name="node_content")
    private String node_content;
    @Column(name="classes")
    private String classes;
    @Column(name="position_x")
    private Integer position_x;
    @Column(name="position_y")
    private Integer position_y;

    public NodeEntity(NodeVO nodeVO){
        this.node_name =nodeVO.getData().name;
        this.node_content =nodeVO.getData().content;
        this.chart_id=nodeVO.getChartId();
        this.classes=nodeVO.getClasses();
        this.position_x=nodeVO.getPosition().x;
        this.position_y=nodeVO.getPosition().y;
        this.node_group=nodeVO.getGroup();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public Integer getChart_id() {
        return chart_id;
    }

    public void setChart_id(Integer chart_id) {
        this.chart_id = chart_id;
    }

    public String getNode_group() {
        return node_group;
    }

    public void setNode_group(String node_group) {
        this.node_group = node_group;
    }

    public String getNode_content() {
        return node_content;
    }

    public void setNode_content(String node_content) {
        this.node_content = node_content;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Integer getPosition_x() {
        return position_x;
    }

    public void setPosition_x(Integer position_x) {
        this.position_x = position_x;
    }

    public Integer getPosition_y() {
        return position_y;
    }

    public void setPosition_y(Integer position_y) {
        this.position_y = position_y;
    }
}
