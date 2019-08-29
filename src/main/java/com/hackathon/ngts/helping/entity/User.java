package com.hackathon.ngts.helping.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author guojiajiong
 * @date 2019-08-29 19:52
 */
@Data
public class User {

    private Integer id;

    private String weixin_id;

    private String name;

    private String avatar;

    private Integer helping_count;

    private Integer black_count;

    private BigDecimal credit_rate;

}
