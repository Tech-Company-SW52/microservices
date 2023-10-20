package com.carrier.carrierservice.service;

import com.carrier.carrierservice.entity.Comment;

import java.util.List;

public interface ICommentService {

    //public List<Comment> findByCarrierId(Long carrierId);
    public List<Comment> findCommentAll();
    public Comment createComment(Comment comment);
    public Comment updateComment(Comment comment);
    public Comment deleteComment(Comment comment);
    public Comment getComment(Long id);

}
