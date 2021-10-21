package com.group8.backend.PO;

import com.group8.backend.VO.EdgeVO;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="t_edge")
@NoArgsConstructor
public class EdgeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="edge_name",unique = true)
    private String edge_name;
    @Column(name="chart_id")
    private Integer chart_id;
    @Column(name="edge_group")
    private String edge_group;
    @Column(name="edge_content")
    private String edge_content;
    @Column(name="classes")
    private String classes;
    @Column(name="source")
    private String source;
    @Column(name="target")
    private String target;

    public EdgeEntity(EdgeVO edgeVO){
        this.id=edgeVO.getId();
        this.edge_group=edgeVO.getGroup();
        this.edge_name=edgeVO.getName();
        this.edge_content=edgeVO.getContent();
        this.chart_id=edgeVO.getChartId();
        this.classes=edgeVO.getClasses();
        this.source=edgeVO.getSource();
        this.target=edgeVO.getTarget();
    }

    public EdgeEntity(String edge_name, Integer chart_id, String edge_group, String edge_content, String classes, String source, String target) {
        this.edge_name = edge_name;
        this.chart_id = chart_id;
        this.edge_group = edge_group;
        this.edge_content = edge_content;
        this.classes = classes;
        this.source = source;
        this.target = target;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEdge_name() {
        return edge_name;
    }

    public void setEdge_name(String edge_name) {
        this.edge_name = edge_name;
    }

    public Integer getChart_id() {
        return chart_id;
    }

    public void setChart_id(Integer chart_id) {
        this.chart_id = chart_id;
    }

    public String getEdge_group() {
        return edge_group;
    }

    public void setEdge_group(String edge_group) {
        this.edge_group = edge_group;
    }

    public String getEdge_content() {
        return edge_content;
    }

    public void setEdge_content(String edge_content) {
        this.edge_content = edge_content;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
