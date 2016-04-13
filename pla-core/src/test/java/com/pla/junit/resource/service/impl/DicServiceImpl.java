package com.pla.junit.resource.service.impl;

import com.pla.junit.resource.dao.DicDao;
import com.pla.junit.resource.model.Dic;
import com.pla.junit.resource.service.DicService;
import com.pla.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DicServiceImpl extends BaseService<Dic> implements DicService {
    @Resource
    private DicDao dicDao;

    @PostConstruct
    public void setDao() {
        setDao(dicDao);
    }

    public List<Dic> findList(Dic dic) {
        return dicDao.findList(dic);
    }
}
