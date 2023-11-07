// package com.fastporte.carrierservice.service.impl;

// import com.fastporte.carrierservice.entity.Comment;
// import com.fastporte.carrierservice.repository.ICommentRepository;
// import com.fastporte.carrierservice.service.ICommentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class CommentServiceImpl implements ICommentService {

//     @Autowired
//     ICommentRepository commentRepository;

//     /*
//     @Override
//     public List<Comment> findByCarrierId(Long carrierId) {
//         return commentRepository.findByCarrierId(carrierId);
//     }

//      */

//     @Override
//     public Comment createComment(Comment comment) {// Crear una nueva instancia de Comment

//         /*
//         comment.setClientId(clientId); // Establecer el ID del cliente asociado al comentario
//         comment.setCarrierId(carrierId); // Establecer el ID del transportista asociado al comentario
//         comment.setCommentText(commentText); // Establecer el texto del comentario
//         */
//         // Guardar el comentario en la base de datos
//         return commentRepository.save(comment);
//     }

//     @Override
//     public Comment updateComment(Comment comment) {
//         // Buscar el comentario por su ID
//         Comment commentDB = getComment(comment.getId());

//         if(commentDB == null){
//             return null;
//         }
//         return commentRepository.save(comment);
//     }

//     @Override
//     public Comment deleteComment(Comment comment) {
//         // Buscar el comentario por su ID
//         Comment commentDB = getComment(comment.getId());

//         if(commentDB == null){
//             return null;
//         }
//         commentRepository.deleteById(comment.getId());
//         return commentDB;
//     }

//     @Override
//     public Comment getComment(Long id) {
//         return commentRepository.findById(id).orElse(null);
//     }

//     @Override
//     public List<Comment> findCommentAll() {
//         // Recuperar todos los comentarios
//         return commentRepository.findAll();
//     }
// }
