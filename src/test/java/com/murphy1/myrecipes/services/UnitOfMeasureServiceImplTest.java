package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.UnitOfMeasure;
import com.murphy1.myrecipes.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository);
    }

    @Test
    void uomList() {
        List<UnitOfMeasure> uomList = new ArrayList<>();
        uomList.add(new UnitOfMeasure());

        when(unitOfMeasureRepository.findAll()).thenReturn(uomList);
        Iterable<UnitOfMeasure> returnedList = service.uomList();

        assertNotNull(uomList);
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}