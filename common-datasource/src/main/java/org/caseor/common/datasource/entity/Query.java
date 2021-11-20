package org.caseor.common.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 分页查询对象
 * @author Fu Kai
 * @since 20201129
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Query implements Serializable {

    /**
     * 分页参数 当前页
     */
    private Integer pageNum = 1;

    /**
     * 分页参数 分页大小 pageSize为-1时查询所有, 不分页
     */
    private Integer pageSize = 10;

    /**
     * 是否开启计数
     */
    private Boolean count = true;

    /**
     * 参数集合
     */
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return Optional.ofNullable(params).orElse(new HashMap<>(0));
    }
}
