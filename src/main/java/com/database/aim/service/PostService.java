package com.database.aim.service;

import com.database.aim.dao.PostDao;
import com.database.aim.pojo.Post;
import com.database.aim.pojo.ShowPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostDao postDao;
    @Autowired
    UserService userService;
    public List<ShowPost> getAllPosts(int poster) {
        List<Post> posts = postDao.findPostsByPoster(poster);
        List<ShowPost> showPosts = new ArrayList<>();
        for(Post it : posts) {
            ShowPost showPost = new ShowPost();
            showPost.setCreatedAt(it.getCreatedAt());
            showPost.setId(it.getId());
            showPost.setPoster(it.getPoster());
            showPost.setPosterName(userService.getUserById(it.getId()).getUsername());
            showPost.setTitle(it.getTitle());
            showPosts.add(showPost);
        }
        return showPosts;
    }

    public void addPost(Post post) {
        postDao.save(post);
    }

    public void deletePost(int postId) {
        postDao.deleteById(postId);
    }

    public List<ShowPost> getRecentPost(int size) {
        List<ShowPost> showPosts = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.DESC,"post.created_at");
        Pageable pageable = PageRequest.of(1,size,sort);
        List<Post> posts =  postDao.findAll();
        for(Post it : posts) {
            ShowPost showPost = new ShowPost();
            showPost.setCreatedAt(it.getCreatedAt());
            showPost.setId(it.getId());
            showPost.setPoster(it.getPoster());
            showPost.setPosterName(userService.getUserById(it.getPoster()).getUsername());
            showPost.setTitle(it.getTitle());
            showPosts.add(showPost);
        }
        return showPosts;
    }

}
