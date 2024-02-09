package ie.ait.agile.agileproject.service;

import ie.ait.agile.agileproject.entity.Hse;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
}
