package com.hackathon.ngts.helping.viewObject;

import com.hackathon.ngts.helping.entity.Helping;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author guojiajiong
 * @date 2019-08-29 20:05
 */
@Data
public class HelpingVo extends Helping {

    private String user_name;

    private String user_weixin_id;

    private Integer user_helping_count;

    private Integer user_black_count;

    private BigDecimal user_credit_rate;

}
