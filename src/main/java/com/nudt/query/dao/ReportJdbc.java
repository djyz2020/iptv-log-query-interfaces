package com.nudt.query.dao;

import com.nudt.query.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/15.
 */
@Service
public interface ReportJdbc extends JpaRepository<ReportEntity, Serializable>{

}
