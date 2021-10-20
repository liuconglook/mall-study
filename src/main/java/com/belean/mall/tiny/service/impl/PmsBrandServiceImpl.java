package com.belean.mall.tiny.service.impl;

import com.github.pagehelper.PageHelper;
import com.belean.mall.tiny.mbg.mapper.PmsBrandMapper;
import com.belean.mall.tiny.mbg.model.PmsBrand;
import com.belean.mall.tiny.mbg.model.PmsBrandExample;
import com.belean.mall.tiny.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PmsBrandService实现类
 */
@Service
@Transactional
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectList(null);
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insert(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateById(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteById(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectList(null);
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectById(id);
    }
}
