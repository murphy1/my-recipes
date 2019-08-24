package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.UnitOfMeasure;
import com.murphy1.myrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public List<UnitOfMeasure> uomList() {
        List<UnitOfMeasure> uomList = new ArrayList<>();
        unitOfMeasureRepository.findAll().forEach(uomList::add);

        return uomList;
    }
}
