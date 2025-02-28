package cn.edu.jlu.controller;

import cn.edu.jlu.po.Comment;
import cn.edu.jlu.po.Result;
import cn.edu.jlu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    //对商家(配送员)评论
    @PostMapping("/comment/save")
    public Result saveComment(Comment comment){
         commentService.saveComment(comment);
         return Result.success();
    }
}
