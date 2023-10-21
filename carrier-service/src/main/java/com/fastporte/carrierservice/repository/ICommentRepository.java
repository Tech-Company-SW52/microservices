package com.fastporte.carrierservice.repository;


import com.fastporte.carrierservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findByCarrierId(Long carrierId);

}
