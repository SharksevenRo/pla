package com.pla.demo.service;

import com.pla.demo.model.Dic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by chey on 2015/9/14.
 */
@Service
@Transactional
public class DicService {

    public void save(Dic dic) {
        try {
            dic.save();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
