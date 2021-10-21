package com.group8.backend.bl;

import com.group8.backend.PO.EdgeEntity;
import com.group8.backend.VO.EdgeVO;
import com.group8.backend.VO.ResponseVO;

public interface EdgeService {
    ResponseVO addEdge(EdgeEntity edgeEntity);

    ResponseVO updateEdge(EdgeEntity edgeEntity);

    ResponseVO deleteEdge(String edge_name, int chart_id);

    EdgeVO getedge(String edge_name, int chart_id);
}
