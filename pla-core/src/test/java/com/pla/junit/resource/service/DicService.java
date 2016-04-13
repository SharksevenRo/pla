package com.pla.junit.resource.service;

import com.pla.junit.resource.model.Dic;
import com.pla.service.IBaseService;

import java.util.List;

public interface DicService extends IBaseService<Dic> {
    List<Dic> findList(Dic dic);
}
