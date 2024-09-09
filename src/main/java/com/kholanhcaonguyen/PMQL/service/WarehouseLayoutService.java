package com.kholanhcaonguyen.PMQL.service;

import com.kholanhcaonguyen.PMQL.entity.WarehouseLayout;
import com.kholanhcaonguyen.PMQL.repository.WarehouseLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseLayoutService {
    @Autowired
    private WarehouseLayoutRepository repository;

    public List<WarehouseLayout> getAllLayouts() {
        return repository.findAll();
    }
}
