package com.database.aim.service;

import com.database.aim.dao.PostDao;
import com.database.aim.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostDao postDao;
    public List<Post> getAllPosts(int poster) {
        return postDao.findPostsByPoster(poster);
    }

    public void addPost(int userId, String title) {
        Post post = new Post();
        post.setTitle(title);
        post.setPoster(userId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setCreatedAt(timestamp);
    }

    public void deletePost(int postId) {
        postDao.deleteById(postId);
    }

}
