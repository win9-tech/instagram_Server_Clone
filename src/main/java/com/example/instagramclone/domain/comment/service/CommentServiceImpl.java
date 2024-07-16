package com.example.instagramclone.domain.comment.service;

import com.example.instagramclone.auth.service.AuthenticationService;
import com.example.instagramclone.domain.comment.dto.CreateCommentRequestDto;
import com.example.instagramclone.domain.comment.entity.Comment;
import com.example.instagramclone.domain.comment.repository.CommentRepository;
import com.example.instagramclone.domain.post.entity.Post;
import com.example.instagramclone.domain.post.repository.PostRepository;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void createComment(Long postId, CreateCommentRequestDto createCommentRequestDto) {

        User user = getAuthenticationUser();
        Post post = getPostById(postId);

        Comment comment = createCommentRequestDto.toEntity();
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Comment comment = findCommentByIdAndPostId(postId, commentId);
        commentRepository.delete(comment);
    }

    private User getAuthenticationUser(){
        Long userId = authenticationService.getAuthenticatedId();
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private Post getPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(()
                -> new EntityNotFoundException("Post not found"));
    }

    private Comment findCommentByIdAndPostId(Long postId, Long commentId){
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found in this post"));
    }
}