package com.group8.backend.controller;

import com.group8.backend.PO.NodeEntity;
import com.group8.backend.VO.NodeVO;
import com.group8.backend.VO.ResponseVO;
import com.group8.backend.bl.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/node")
public class NodeController {
    @Autowired
    NodeService nodeService;

    @PostMapping("/add")
    public ResponseVO addNode(@RequestBody NodeVO nodeVO){
        System.out.println(nodeVO);
        return nodeService.addNode(new NodeEntity(nodeVO));
    }

    @PostMapping("/update")
    public ResponseVO updateNode(@RequestBody NodeVO nodeVO){
        return nodeService.updateNode(new NodeEntity(nodeVO));
    }

    @GetMapping("/delete/{chart_id}/{node_name}")
    public ResponseVO deleteNode(@PathVariable int chart_id, @PathVariable String node_name){
        return nodeService.deleteNode(node_name,chart_id);
    }

    @GetMapping("/get/{chart_id}/{node_name}")
    public ResponseVO findNode(@PathVariable int chart_id,@PathVariable String node_name){
        NodeVO res=nodeService.getNode(node_name,chart_id);
        if(res==null){
            return ResponseVO.buildFailure("没有这样的节点");
        }
        return ResponseVO.buildSuccess(res);
    }

}
