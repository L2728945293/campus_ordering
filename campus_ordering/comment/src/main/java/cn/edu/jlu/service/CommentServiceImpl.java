package cn.edu.jlu.service;


import cn.edu.jlu.mapper.CommentMapper;
import cn.edu.jlu.po.Comment;
import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService{
    @Resource
    private CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {
        comment.setCreate_time(LocalDateTime.now());
       int flag= commentMapper.insert(comment);
       if(flag==1) return;
       else throw new RuntimeException("评论失败");
    }
}
