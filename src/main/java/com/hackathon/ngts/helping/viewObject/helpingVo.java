package com.hackathon.ngts.helping.viewObject;

import com.hackathon.ngts.helping.entity.helping;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author guojiajiong
 * @date 2019-08-29 20:05
 */
@Data
public class helpingVo extends helping {

    private String user_name;

    private Integer user_helping_count;

    private Integer user_black_count;

    private BigDecimal user_credit_rate;

}
