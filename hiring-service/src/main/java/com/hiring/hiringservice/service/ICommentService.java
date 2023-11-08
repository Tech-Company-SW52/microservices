package com.hiring.hiringservice.service;

import com.hiring.hiringservice.entity.Comment;

import java.util.List;

public interface ICommentService {

    public List<Comment> findCommentAll();

    public List<Comment> findByContractId(Long contractId);

    public Comment createComment(Comment comment, Long clientId, Long contractId);

    public Comment updateComment(Comment comment);

    public Comment deleteComment(Comment comment);

    public Comment getComment(Long id);
}
