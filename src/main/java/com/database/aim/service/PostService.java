package com.database.aim.service;

import com.database.aim.dao.PostDao;
import com.database.aim.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostDao postDao;
    List<Post> findAllPosts(int poster) {
        return postDao.findPostsByPoster(poster);
    }
}
