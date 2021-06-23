package com.database.aim.controller;

import com.database.aim.pojo.Post;
import com.database.aim.pojo.ShowPost;
import com.database.aim.service.PostService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Api
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/post/getPosts")
    public List<ShowPost> getPostsByPoster(@RequestBody int poster) {
        List<ShowPost> posts = new ArrayList<>();
        try {
            posts = postService.getAllPosts(poster);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @PostMapping("/post/addPost")
    public void addPost(@RequestBody Post post) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            post.setCreatedAt(timestamp);
            postService.addPost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/post/deletePost")
    public void deletePost(@RequestBody int id) {
        try {
            postService.deletePost(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/post/getRecentPosts")
    public List<ShowPost> getRecentPosts() {
        List<ShowPost> showPosts = new ArrayList<>();
        try {
            showPosts = postService.getRecentPost(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showPosts;
    }

}
