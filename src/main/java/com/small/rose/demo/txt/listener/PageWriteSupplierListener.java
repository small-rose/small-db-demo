package com.small.rose.demo.txt.listener;


import com.small.rose.demo.txt.function.QueryPageList;
import lombok.Getter;

import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/8 23:53
 * @version: v1.0
 */
public class PageWriteSupplierListener<T> implements TxtSupplierListener<T> {


    @Getter
    private int pageNo ;
    @Getter
    private int pageSize ;
    private List<T> list ;
    private QueryPageList<List<T>> queryPageList;


    public PageWriteSupplierListener(int pageNo, int pageSize ,QueryPageList<List<T>> queryPageList) {
        this.pageNo = pageNo;
        this.pageSize = pageSize ;
        this.queryPageList = queryPageList;
    }
    @Override
    public List<T> queryData() {
        list = queryPageList.query(pageNo, pageSize);
        return list;
    }

}
