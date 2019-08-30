package com.hackathon.ngts.helping.dao;

import com.hackathon.ngts.helping.entity.Article;
import com.hackathon.ngts.helping.entity.Category;
import com.hackathon.ngts.helping.entity.Helping;
import com.hackathon.ngts.helping.viewObject.ArticleVo;
import com.hackathon.ngts.helping.viewObject.HelpingArticleVo;
import com.hackathon.ngts.helping.viewObject.HelpingVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author guojiajiong
 * @date 2019-08-29 19:40
 */

@Mapper
@Repository
public interface HelpingDao {

    @Select(value = {"select id,category_name,img_url,description from t_category where is_deleted=0 "})
    List<Category> getCategoryList();

    @Select(value = {"select a.id,a.title,a.helping_url,a.content,a.category_id,c.img_url,c.category_name," +
            "a.`type`,a.end_flg,a.owner_user_id,a.end_time," +
            "u.`name` as owner_name,u.avatar as owner_avatar,u.weixin_id as owner_weixin_id,u.helping_count as owner_helping_count," +
            " u.black_count as owner_black_count, u.credit_rate as owner_credit_rate " +
            " from t_user u,t_article a" +
            " LEFT JOIN t_category c" +
            " ON a.category_id = c.id" +
            " where a.owner_user_id=u.id" +
            " and a.end_time > now()" +
            " order by a.create_time desc" +
            " limit #{limit} offset #{offset}"})
    List<ArticleVo> getArticleList(Map<String, Object> param);

    @Select(value = {"select a.id,a.title,a.helping_url,a.content,a.category_id,c.img_url,c.category_name," +
            "a.`type`,a.end_flg,a.owner_user_id,a.end_time" +
            " from t_article a" +
            " LEFT JOIN t_category c" +
            " ON a.category_id = c.id" +
            " where a.owner_user_id = #{user_id}" +
            " order by a.create_time desc" +
            " limit #{limit} offset #{offset}"})
    List<ArticleVo> getMyArticleList(Map<String, Object> param);


    @Select(value = {"select h.user_id,h.confirm_flg,u.weixin_id," +
            "u.`name` as user_name, u.avatar as user_avatar, u.helping_count as user_helping_count" +
            ",u.black_count as user_black_count, u.credit_rate as user_credit_rate" +
            " from t_helping h,t_user u" +
            " where h.user_id = u.id" +
            " and h.article_id=#{article_id}" +
            " order by h.create_time" +
            " limit #{limit} offset #{offset}"})
    List<HelpingVo> getHelpingList(Map<String, Object> param);

    @Select(value = {"select a.id, a.title, a.helping_url,a.content,a.category_id,c.category_name," +
            "a.`type`,a.end_flg,a.owner_user_id,u.name as owner_user_name, u.avatar as owner_avatar,h.confirm_flg,h.create_time" +
            " from t_helping h,t_article a" +
            " LEFT JOIN t_category c" +
            " ON a.category_id = c.id" +
            " LEFT JOIN t_user u" +
            " ON a.owner_user_id = u.id" +
            " where h.article_id = a.id" +
            " and h.user_id=#{user_id}" +
            " order by h.create_time desc" +
            " limit #{limit} offset #{offset}"})
    List<HelpingArticleVo> getMyHelpingList(Map<String, Object> param);


    @Insert(value = {"INSERT INTO `t_article` (",
            "`title`, `helping_url`, `content`, `category_id`, `type`, `end_flg`, `owner_user_id`, `create_time`, `end_time`) ",
            "VALUES(",
            "#{title},#{helping_url},#{content},#{category_id},#{type},#{end_flg},#{owner_user_id},#{create_time},#{end_time}",
            ")"})
    int inserArticle(Article article);


    @Insert(value = {"INSERT INTO `t_helping` (",
            "`user_id`, `article_id`, `confirm_flg`, `create_time`) ",
            "VALUES(",
            "#{user_id},#{article_id},0,#{article_id}",
            ")"})
    int inserHelping(Helping helping);


    @Update(value = {"update t_helping set confirm_flg=1 where user_id = #{user_id} and article_id = #{article_id} "})
    int confirmHelping(Map<String, Object> param);

    @Update(value = {"update t_user set helping_count=helping_count+1 where id=#{id};"})
    int addHelpingCount(Integer id);

}


