package com.group8.backend.controller;


import com.group8.backend.PO.EdgeEntity;
import com.group8.backend.VO.EdgeVO;
import com.group8.backend.VO.ResponseVO;
import com.group8.backend.bl.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/edge")
public class EdgeController {
    @Autowired
    EdgeService edgeService;

    @PostMapping("/add")
    public ResponseVO addEdge(@RequestBody EdgeVO edgeVO){
        return edgeService.addEdge(new EdgeEntity(edgeVO));
    }

    @PostMapping("/update")
    public ResponseVO updateEdge(@RequestBody EdgeVO edgeVO){
        return edgeService.updateEdge(new EdgeEntity(edgeVO));
    }

    @GetMapping("/delete/{chart_id}/{edge_name}")
    public ResponseVO deleteEdge(@PathVariable int chart_id, @PathVariable String edge_name){
        return edgeService.deleteEdge(edge_name,chart_id);
    }

    @GetMapping("/get/{chart_id}/{edge_name}")
    public ResponseVO findedge(@PathVariable int chart_id,@PathVariable String edge_name){
        EdgeVO res=edgeService.getedge(edge_name,chart_id);
        if(res==null){
            return ResponseVO.buildFailure("没有这样的关系");
        }
        return ResponseVO.buildSuccess(res);
    }
}
