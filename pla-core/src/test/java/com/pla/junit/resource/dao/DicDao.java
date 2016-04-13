package com.pla.junit.resource.dao;

import com.pla.dao.IBaseDAO;
import com.pla.junit.resource.model.Dic;

import java.util.List;

public interface DicDao extends IBaseDAO<Dic> {
    List<Dic> findList(Dic dic);
}
