package com.hackathon.ngts.helping.controller;

import com.hackathon.ngts.helping.entity.Article;
import com.hackathon.ngts.helping.entity.Category;
import com.hackathon.ngts.helping.entity.Helping;
import com.hackathon.ngts.helping.service.HelpingService;
import com.hackathon.ngts.helping.viewObject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author guojiajiong
 * @date 2019-08-29 21:42
 */
@RequestMapping("helping/api")
@RestController
public class HelpingController {

    @Autowired
    HelpingService helpingService;

    @GetMapping("getCategoryList")
    private ResultPage<Category> getCategoryList(){
        ResultPage<Category> resultPage = new ResultPage();
        try{
            resultPage.setList(helpingService.getCategoryList());
            resultPage.setError(false);
            resultPage.setErrorMessage("");
        }catch (Exception e){
            resultPage.setError(true);
            resultPage.setErrorMessage(e.getMessage());
        }
        return resultPage;
    }

    @GetMapping("getArticleList")
    private ResultPage<ArticleVo> getArticleList(@RequestParam(required = false) Integer pageNum){
        ResultPage<ArticleVo> resultPage = new ResultPage();
        try{
            resultPage.setList(helpingService.getArticleList(pageNum));
            resultPage.setError(false);
            resultPage.setErrorMessage("");
        }catch (Exception e){
            resultPage.setError(true);
            resultPage.setErrorMessage(e.getMessage());
        }
        return resultPage;
    }

    @GetMapping("getMyArticleList")
    private ResultPage<ArticleVo> getMyArticleList(@RequestParam(required = false) Integer pageNum){
        ResultPage<ArticleVo> resultPage = new ResultPage();
        try{
            resultPage.setList(helpingService.getMyArticleList(pageNum));
            resultPage.setError(false);
            resultPage.setErrorMessage("");
        }catch (Exception e){
            resultPage.setError(true);
            resultPage.setErrorMessage(e.getMessage());
        }
        return resultPage;
    }

    @GetMapping("getHelpingList")
    private ResultPage<HelpingVo> getHelpingList(@RequestParam String article_id, @RequestParam(required = false) Integer pageNum){
        ResultPage<HelpingVo> resultPage = new ResultPage();
        try{
            resultPage.setList(helpingService.getHelpingList(article_id,pageNum));
            resultPage.setError(false);
            resultPage.setErrorMessage("");
        }catch (Exception e){
            resultPage.setError(true);
            resultPage.setErrorMessage(e.getMessage());
        }
        return resultPage;
    }

    @GetMapping("getMyHelpingList")
    private ResultPage<HelpingArticleVo> getMyHelpingList(@RequestParam(required = false) Integer pageNum){
        ResultPage<HelpingArticleVo> resultPage = new ResultPage();
        try{
            resultPage.setList(helpingService.getMyHelpingList(pageNum));
            resultPage.setError(false);
            resultPage.setErrorMessage("");
        }catch (Exception e){
            resultPage.setError(true);
            resultPage.setErrorMessage(e.getMessage());
        }
        return resultPage;
    }

    @PostMapping("insertArticle")
    private ResultVo insertArticle(@RequestBody Article article){
        ResultVo resultVo = new ResultVo();
        try{
            //todo 当前用户

            helpingService.insertArticle(article);
            resultVo.setError(false);
            resultVo.setErrorMessage("");
        }catch (Exception e){
            resultVo.setError(true);
            resultVo.setErrorMessage(e.getMessage());
        }
        return resultVo;
    }

    @PostMapping("insertHelping")
    private ResultVo insertHelping(@RequestBody Helping helping){
        ResultVo resultVo = new ResultVo();
        try{
            //todo 当前用户
            helping.setUser_id(1);
            helpingService.insertHelping(helping);
            resultVo.setError(false);
            resultVo.setErrorMessage("");
        }catch (Exception e){
            resultVo.setError(true);
            resultVo.setErrorMessage(e.getMessage());
        }
        return resultVo;
    }

    @PostMapping("confirmHelping")
    private ResultVo confirmHelping(@RequestBody Helping helping){
        ResultVo resultVo = new ResultVo();
        try{
            helpingService.confirmHelping(helping.getArticle_id(), helping.getUser_id());
            resultVo.setError(false);
            resultVo.setErrorMessage("");
        }catch (Exception e){
            resultVo.setError(true);
            resultVo.setErrorMessage(e.getMessage());
        }
        return resultVo;
    }


}
