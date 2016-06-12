package com.ricamgar.notify.data.mapper;

public interface Mapper<T, R> {
    R map(T model);
}
