package com.kholanhcaonguyen.PMQL.controller;

import com.kholanhcaonguyen.PMQL.entity.WarehouseLayout;
import com.kholanhcaonguyen.PMQL.service.WarehouseLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse-layouts")
@CrossOrigin(origins = "http://localhost:3000")
public class WarehouseLayoutController {
    @Autowired
    private WarehouseLayoutService service;

    @GetMapping
    public List<WarehouseLayout> getAllLayouts() {
        return service.getAllLayouts();
    }
}
