package com.small.rose.demo.module.jdbc;

import com.small.rose.demo.module.hibernate.entities.Torder;
import com.small.rose.demo.module.mybatis.entity.Tuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ JdbcQueryService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/19 16:40
 * @Version ： 1.0
 **/

@Service
public class JdbcQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate ;


    public Tuser queryById(Long id){
        String sql = "select * from t_user where id = "+id;
        return  jdbcTemplate.queryForObject(sql, Tuser.class);
    }



    public List<Torder> queryListByStatus(List<Object> param){
        String sql = "select * from t_torder where order_status= ? ";
        return  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Torder.class),param.toArray());
    }
}
