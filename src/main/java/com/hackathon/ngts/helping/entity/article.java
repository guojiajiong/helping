package com.hackathon.ngts.helping.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author guojiajiong
 * @date 2019-08-29 19:47
 */
@Data
public class article {

    private Integer id;

    private String title;

    private String helping_url;

    private String content;

    private Integer category_id;

    private Integer type;

    private Integer end_flg;

    private String owner_user_id;

    private Date create_time;

    private Date end_time;

}
