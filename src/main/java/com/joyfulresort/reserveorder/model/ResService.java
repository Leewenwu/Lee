package com.joyfulresort.reserveorder.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("resService")
public class ResService {

    @Autowired
    private ResRepository repository;

    public void addRes(ResVO resVO) {
        repository.save(resVO);
    }

    public void updateRes(ResVO resVO) {
        repository.save(resVO);
    }

    public void deleteRes(Integer resId) {
        if (repository.existsById(resId)) {
            repository.deleteById(resId);
        }
    }

    public ResVO getOneRes(Integer resId) {
        Optional<ResVO> optional = repository.findById(resId);
        return optional.orElse(null);
    }

    public List<ResVO> getAllRes() {
//        return repository.findAll(); 預設方法
    	return repository.findAllRes();
    }

    
//    ------------------------------------複合查詢
    
    

    
}
