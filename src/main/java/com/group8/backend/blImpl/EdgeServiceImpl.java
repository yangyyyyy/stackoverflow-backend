package com.group8.backend.blImpl;

import com.group8.backend.PO.EdgeEntity;
import com.group8.backend.Repository.EdgeRepository;
import com.group8.backend.VO.EdgeVO;
import com.group8.backend.VO.ResponseVO;
import com.group8.backend.bl.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EdgeServiceImpl implements EdgeService {
    @Autowired
    EdgeRepository edgeRepository;

    @Override
    public ResponseVO addEdge(EdgeEntity edgeEntity) {
        EdgeEntity saveEdge= edgeRepository.save(edgeEntity);
        if(saveEdge!=null){
            return ResponseVO.buildSuccess("添加关系成功");
        }
        return ResponseVO.buildFailure("添加关系失败");
    }

    @Override
    public ResponseVO updateEdge(EdgeEntity edgeEntity) {
        List<EdgeEntity> chosenEdgeList=edgeRepository.findByEdge_Name(edgeEntity.getChart_id(),edgeEntity.getEdge_name());
        if(chosenEdgeList.size()==0){
            return ResponseVO.buildFailure("不存在这样的关系，请先保存该关系");
        }
        EdgeEntity chosenEdge=chosenEdgeList.get(0);
        edgeRepository.delete(chosenEdge);
        edgeRepository.save(edgeEntity);
        return ResponseVO.buildSuccess("成功更新该关系");
    }

    @Override
    public ResponseVO deleteEdge(String edge_name, int chart_id) {
        List<EdgeEntity> chosenEdgeList=edgeRepository.findByEdge_Name(chart_id,edge_name);
        if(chosenEdgeList.size()==0){
            return ResponseVO.buildFailure("不存在这样的关系，请先保存该关系");
        }
        EdgeEntity chosenEdge=chosenEdgeList.get(0);
        edgeRepository.delete(chosenEdge);
        return ResponseVO.buildSuccess("成功删除该关系");
    }

    @Override
    public EdgeVO getedge(String edge_name, int chart_id) {
        List<EdgeEntity> chosenEdgeList=edgeRepository.findByEdge_Name(chart_id,edge_name);
        if(chosenEdgeList.size()==0){
            return null;
        }
        return new EdgeVO(chosenEdgeList.get(0));
    }

    @Override
    public List<EdgeVO> findEdgeByChart_id(int chart_id) {
        List<EdgeEntity> chosenEdgeList=edgeRepository.findByChart_id(chart_id);
        if(chosenEdgeList.size()==0){
            return null;
        }
        List<EdgeVO> returnEdgeVO=new ArrayList<EdgeVO>();
        for(EdgeEntity edgeEntity:chosenEdgeList){
            returnEdgeVO.add(new EdgeVO(edgeEntity));
        }
        return returnEdgeVO;
    }
}
