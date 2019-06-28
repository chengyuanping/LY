package com.leyou.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroups(@PathVariable("cid") Long id){
        List<SpecGroup> SpecGroups=specService.querySpecGroups(id);
        if(SpecGroups!=null&&SpecGroups.size()>0){
            return ResponseEntity.ok(SpecGroups);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParam(
            @RequestParam(value="gid",required = false) Long gid,
            @RequestParam(value="cid",required = false) Long cid,
            @RequestParam(value="searching",required = false) Boolean searching,
            @RequestParam(value="generic",required = false) Boolean generic
    ){
        List<SpecParam> SpecParamList = specService.querySpecParam(gid,cid,searching,generic);
        if(SpecParamList!=null&&SpecParamList.size()>0){
            return ResponseEntity.ok(SpecParamList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
