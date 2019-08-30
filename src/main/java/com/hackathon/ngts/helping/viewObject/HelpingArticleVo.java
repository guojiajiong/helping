package com.hackathon.ngts.helping.viewObject;

import com.hackathon.ngts.helping.entity.Article;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author guojiajiong
 * @date 2019-08-29 19:58
 */
@Data
public class HelpingArticleVo extends ArticleVo {

    private Integer confirm_flg;

    private Date create_time;
}

