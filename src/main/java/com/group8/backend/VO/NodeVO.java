package com.group8.backend.VO;

import com.group8.backend.PO.NodeEntity;
import lombok.NoArgsConstructor;

/**
 * @ClassName NodeVO
 * @Description VO for node related parameters
 * @Author 李俊杰
 * @Date 2021/10/14/20:23
 **/
@NoArgsConstructor
public class NodeVO {
    public class Data{
        public String name;
        public String content;
    }

    public class Position{
        public int x;
        public int y;
    }

    private Data data;
    private String classes;
    private Position position;
    private int chartId;
    private String group;

    public NodeVO(NodeEntity nodeEntity){
        this.group=nodeEntity.getNode_group();
        this.data=new Data();
        this.data.name = nodeEntity.getNode_name();
        this.data.content = nodeEntity.getNode_content();
        this.classes= nodeEntity.getClasses();
        this.position=new Position();
        this.position.x= nodeEntity.getPosition_x();
        this.position.y= nodeEntity.getPosition_y();
        this.chartId= nodeEntity.getChart_id();
    }

    public NodeVO(String group, String name, String content, String classes, int x, int y, int chartId){
        this.group=group;
        this.data= new Data();
        this.data.name = name;
        this.data.content = content;
        this.classes= classes;
        this.position= new Position();
        this.position.x= x;
        this.position.y= y;
        this.chartId= chartId;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
