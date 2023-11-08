package com.hiring.hiringservice.service.impl;

import com.hiring.hiringservice.client.ClientClient;
import com.hiring.hiringservice.entity.Comment;
import com.hiring.hiringservice.entity.User;
import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.repository.ICommentRepository;
import com.hiring.hiringservice.repository.IContractRepository;
import com.hiring.hiringservice.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    IContractRepository contractRepository;
    @Autowired
    ClientClient clientClient;

    @Override
    public List<Comment> findCommentAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByContractId(Long contractId) {
        List<Comment> commentsDB = commentRepository.findAll();
        return commentsDB.stream()
                .filter(comment -> comment.getContract().getId().equals(contractId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByCarrierId(Long carrierId) {
        List<Comment> commentsDB = commentRepository.findAll();
        return commentsDB.stream()
                .filter(comment -> comment.getContract().getCarrier().getId().equals(carrierId))
                .collect(Collectors.toList());
    }

    @Override
    public Comment createComment(Comment comment, Long clientId, Long contractId) {
        User clientDB = clientClient.getClient(clientId).getBody();
        if (clientDB == null)
            return null;
        Contract contractDB = contractRepository.findById(contractId).orElse(null);
        if (contractDB == null)
            return null;

        // Update stars
        User carrierDB = contractDB.getCarrier();
        int currentStars = carrierDB.getStars();
        if (currentStars == 0) {
            carrierDB.setStars(comment.getStars());
        } else {
            carrierDB.setStars((currentStars + comment.getStars()) / 2);
        }

        comment.setClient(clientDB);
        comment.setContract(contractDB);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment commentDB = getComment(comment.getId());
        if (commentDB == null) {
            return null;
        }
        commentDB.setStars(comment.getStars());
        commentDB.setComment(comment.getComment());

        // Update stars
        User carrierDB = commentDB.getContract().getCarrier();
        List<Comment> commentsCarrierDB = findByCarrierId(carrierDB.getId());

        commentDB.getContract().getCarrier().setStars(
                commentsCarrierDB.stream()
                        .mapToInt(Comment::getStars)
                        .sum() / commentsCarrierDB.size());

        return commentRepository.save(commentDB);
    }

    @Override
    public Comment deleteComment(Comment comment) {
        Comment commentDB = getComment(comment.getId());
        if (commentDB == null) {
            return null;
        }
        commentRepository.deleteById(comment.getId());
        return commentDB;
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }
}
