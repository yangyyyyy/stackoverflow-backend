package com.group8.backend.blImpl;

import com.group8.backend.PO.NodeEntity;
import com.group8.backend.Repository.NodeRepository;
import com.group8.backend.VO.NodeVO;
import com.group8.backend.VO.ResponseVO;
import com.group8.backend.bl.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    NodeRepository nodeRepository;

    @Override
    public ResponseVO addNode(NodeEntity nodeEntity) {
        NodeEntity saveNode= nodeRepository.save(nodeEntity);
        if(saveNode!=null){
            return ResponseVO.buildSuccess("添加节点成功");
        }
        return ResponseVO.buildFailure("添加节点失败");
    }

    @Override
    public ResponseVO updateNode(NodeEntity nodeEntity) {
        List<NodeEntity> chosenNodeList=nodeRepository.findByNode_Name(nodeEntity.getChart_id(),nodeEntity.getNode_name());
        if(chosenNodeList.size()==0){
            return ResponseVO.buildFailure("不存在这样的节点，请先保存该节点");
        }
        NodeEntity chosenNode=chosenNodeList.get(0);
        nodeRepository.delete(chosenNode);
        nodeRepository.save(nodeEntity);
        return ResponseVO.buildSuccess("成功更新节点");
    }

    @Override
    public ResponseVO deleteNode(String node_name, int chart_id) {
        List<NodeEntity> chosenNodeList=nodeRepository.findByNode_Name(chart_id,node_name);
        if(chosenNodeList.size()==0){
            return ResponseVO.buildFailure("不存在这样的节点，请先保存该节点");
        }
        NodeEntity chosenNode=chosenNodeList.get(0);
        nodeRepository.delete(chosenNode);

        return ResponseVO.buildSuccess("删除成功");
    }

    @Override
    public NodeVO getNode(String node_name, int chart_id) {
        List<NodeEntity> chosenNodeList=nodeRepository.findByNode_Name(chart_id,node_name);
        if(chosenNodeList.size()==0){
            return null;
        }
        return new NodeVO(chosenNodeList.get(0));
    }

    @Override
    public List<NodeVO> findNodeByChart_id(int chart_id) {
        List<NodeEntity> chosenNodeList=nodeRepository.findByChart_id(chart_id);
        if(chosenNodeList.size()==0){
            return null;
        }
        List<NodeVO> returnNodeVO=new ArrayList<NodeVO>();
        for(NodeEntity nodeEntity:chosenNodeList){
            returnNodeVO.add(new NodeVO(nodeEntity));
        }
        return returnNodeVO;
    }


}
