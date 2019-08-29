package com.hackathon.ngts.helping.viewObject;

import com.hackathon.ngts.helping.entity.article;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author guojiajiong
 * @date 2019-08-29 19:58
 */
@Data
public class articleVo extends article {

    private String category_name;

    private String owner_name;

    private Integer owner_helping_count;

    private Integer owner_black_count;

    private BigDecimal owner_credit_rate;

    private Long remain_time_million;

}

