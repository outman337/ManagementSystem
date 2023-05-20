package com.backend.management.service;

import com.backend.management.exception.PostNotExistException;
import com.backend.management.model.Post;
import com.backend.management.model.User;
import com.backend.management.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //1.add post
    public void add(Post post) {
        postRepository.save(post);
    }

    //2.post by user
    public List<Post> postByUser(String username) {
        User.Builder userBuilder = new User.Builder();
        userBuilder.setUsername(username);
        User user = userBuilder.build();
        return postRepository.findByUserId(user);
    }

    //3.get post by id
    public Post findByIdAndUser(Long postId, String username) throws PostNotExistException {
        Post post = postRepository.findByIdAndUserId(postId, new User.Builder().setUsername(username).build());

        if(post == null) {
            throw new PostNotExistException("Post doesn't exist");
        }
        return post;
    }

    //4.delete post by id
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long postId, String username) throws PostNotExistException {
        Post post = postRepository.findByIdAndUserId(postId, new User.Builder().setUsername(username).build());

        if(post == null) {
            throw new PostNotExistException("Post doesn't exist");
        }
        postRepository.deleteById(postId);
    }

    //5.get all posts
    public List<Post> allPosts(String username) {
        return postRepository.findAll();
    }

}
