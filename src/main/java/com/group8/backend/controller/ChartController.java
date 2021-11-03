package com.group8.backend.controller;

import com.group8.backend.VO.EdgeVO;
import com.group8.backend.VO.EntityVO;
import com.group8.backend.VO.NodeVO;
import com.group8.backend.VO.ResponseVO;
import com.group8.backend.bl.EdgeService;
import com.group8.backend.bl.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/chart")
public class ChartController {
    @Autowired
    EdgeService edgeService;
    @Autowired
    NodeService nodeService;

    // This is ia test api for remote deploy
    @GetMapping("/hello")
    public ResponseVO hello(){
        return ResponseVO.buildSuccess("hello");
    }

    //查找图
    @GetMapping("/getChart/{id}")
    public ResponseVO getChart(@PathVariable int id){
        List<NodeVO> nodeRes= nodeService.findNodeByChart_id(id);
        if(nodeRes==null){
            return ResponseVO.buildFailure("图中没有节点，认为没有这样的图");
        }
        List<EntityVO> res = new ArrayList<EntityVO>(nodeRes);
        List<EdgeVO> edgeRes= edgeService.findEdgeByChart_id(id);
        if(edgeRes!=null){
            res.addAll(edgeRes);
        }
        return ResponseVO.buildSuccess(res);
    }

}
