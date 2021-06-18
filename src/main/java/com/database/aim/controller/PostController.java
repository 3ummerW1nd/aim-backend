package com.database.aim.controller;

import com.database.aim.pojo.Post;
import com.database.aim.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/post/getPosts")
    public List<Post> getPostsByPoster(@RequestBody int poster) {
        List<Post> posts = new ArrayList<>();
        try {
            posts = postService.getAllPosts(poster);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @PostMapping("/post/addPost")
    public void addPost(@RequestBody String title, @RequestBody int poster) {
        try {
            postService.addPost(poster, title);
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

}
