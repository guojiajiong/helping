package com.hackathon.ngts.helping.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author guojiajiong
 * @date 2019-08-29 19:55
 */
@Data
public class Helping {

    private Integer user_id;

    private Integer article_id;

    private Integer confirm_flg;

    private Date create_time;


}
