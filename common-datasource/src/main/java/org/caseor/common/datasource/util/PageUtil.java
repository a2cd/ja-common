package org.caseor.common.datasource.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.caseor.common.datasource.entity.Query;


/**
 * 分页工具类
 * @author Fu Kai
 * @since 20210109
 */

public class PageUtil {

    /**
     * 根据分页参数封装分页对象
     * @return Page
     */
    public static <T> Page<T> getPage(Query query) {
        Page<T> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        OrderItem orderItem = new OrderItem();
        page.addOrder(orderItem);
        page.setSearchCount(query.getCount());
        return page;
    }
}
