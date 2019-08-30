package com.hackathon.ngts.helping.service;

import com.hackathon.ngts.helping.auth.Authorization;
import com.hackathon.ngts.helping.auth.HelpingUser;
import com.hackathon.ngts.helping.dao.HelpingDao;
import com.hackathon.ngts.helping.entity.Article;
import com.hackathon.ngts.helping.entity.Category;
import com.hackathon.ngts.helping.entity.Helping;
import com.hackathon.ngts.helping.viewObject.ArticleVo;
import com.hackathon.ngts.helping.viewObject.HelpingArticleVo;
import com.hackathon.ngts.helping.viewObject.HelpingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author guojiajiong
 * @date 2019-08-29 21:03
 */
@Service
public class HelpingService {

    @Autowired
    HelpingDao helpingDao;

    public List<Category> getCategoryList(){
        return helpingDao.getCategoryList();
    }

    public List<ArticleVo> getArticleList(Integer pageNum){
        int limit = 20;
        if(pageNum==null) pageNum=1;
        int offset = (pageNum-1)*limit;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);

        List<ArticleVo> articleVoList = helpingDao.getArticleList(params);
        Date now = new Date();
        for(ArticleVo vo : articleVoList){
            if(vo.getEnd_time()!=null){
                vo.setRemain_time_million(vo.getEnd_time().getTime() - now.getTime());
            }else{
                vo.setRemain_time_million(0L);
            }
        }

        return articleVoList;
    }

    public List<ArticleVo> getMyArticleList(Integer pageNum){
        int limit = 20;
        if(pageNum==null) pageNum=1;
        int offset = (pageNum-1)*limit;

        Map<String, Object> params = new HashMap<>();
        //当前用户
        HelpingUser user = Authorization.get();
        if(user!=null && user.getId()!=0) {
            params.put("user_id", user.getId());
        }else{
            throw new RuntimeException("未获得登录用户");
        }

        //params.put("user_id", 1);
        params.put("limit", limit);
        params.put("offset", offset);

        List<ArticleVo> articleVoList = helpingDao.getMyArticleList(params);
        Date now = new Date();
        for(ArticleVo vo : articleVoList){
            if(vo.getEnd_time()!=null){
                vo.setRemain_time_million(vo.getEnd_time().getTime() - now.getTime());
            }else{
                vo.setRemain_time_million(0L);
            }
        }
        return articleVoList;
    }

    public List<HelpingVo> getHelpingList(String article_id, Integer pageNum){
        int limit = 20;
        if(pageNum==null) pageNum=1;
        int offset = (pageNum-1)*limit;

        Map<String, Object> params = new HashMap<>();
        params.put("article_id", article_id);
        params.put("limit", limit);
        params.put("offset", offset);

        return helpingDao.getHelpingList(params);
    }

    public List<HelpingArticleVo> getMyHelpingList(Integer pageNum){
        int limit = 20;
        if(pageNum==null) pageNum=1;
        int offset = (pageNum-1)*limit;

        Map<String, Object> params = new HashMap<>();
        //params.put("user_id", 1);
        params.put("limit", limit);
        params.put("offset", offset);
        //当前用户
        HelpingUser user = Authorization.get();
        if(user!=null && user.getId()!=0) {
            params.put("user_id", user.getId());
        }else{
            throw new RuntimeException("未获得登录用户");
        }

        return helpingDao.getMyHelpingList(params);
    }


    public int insertArticle(Article article){
        //默认当天
        if(article.getEnd_time()==null){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            calendar.add(Calendar.DAY_OF_MONTH,1);
            article.setEnd_time(calendar.getTime());
        }

        article.setCreate_time(new Date());
        return helpingDao.inserArticle(article);
    }

    public int insertHelping(Helping helping){
        helping.setCreate_time(new Date());
        return helpingDao.inserHelping(helping);
    }

    public int confirmHelping(Integer article_id, Integer user_id){

        Map<String, Object> params = new HashMap<>();
        params.put("article_id", article_id);
        params.put("user_id", user_id);

        helpingDao.confirmHelping(params);
        helpingDao.addHelpingCount(user_id);
        return 0;
    }

}
