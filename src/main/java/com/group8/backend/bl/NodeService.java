package com.group8.backend.bl;

import com.group8.backend.PO.NodeEntity;
import com.group8.backend.VO.NodeVO;
import com.group8.backend.VO.ResponseVO;

import java.util.List;

public interface NodeService {
    ResponseVO addNode(NodeEntity nodeEntity);

    ResponseVO updateNode(NodeEntity nodeEntity);

    ResponseVO deleteNode(String node_name, int chart_id);

    NodeVO getNode(String node_name, int chart_id);

    List<NodeVO> findNodeByChart_id(int chart_id);
}
