package com.group8.backend.VO;

import com.group8.backend.PO.EdgeEntity;

public class EdgeVO extends EntityVO{
    private int id;
    private String name;
    private String content;
    private String source;
    private String target;
    private String classes;
    private int chartId;
    private String group;

    public EdgeVO(EdgeEntity edgeEntity){
        this.group=edgeEntity.getEdge_group();
        this.id=edgeEntity.getId();
        this.name=edgeEntity.getEdge_name();
        this.content=edgeEntity.getEdge_content();
        this.source=edgeEntity.getSource();
        this.target=edgeEntity.getTarget();
        this.classes=edgeEntity.getClasses();
        this.chartId= edgeEntity.getChart_id();
    }

    public EdgeVO(int id, String name, String content, String source, String target, String classes, int chartId, String group) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.source = source;
        this.target = target;
        this.classes = classes;
        this.chartId = chartId;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getChartId() {
        return chartId;
    }

    public void setChartId(int chartId) {
        this.chartId = chartId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
