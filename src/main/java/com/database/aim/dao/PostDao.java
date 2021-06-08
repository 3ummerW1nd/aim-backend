package com.database.aim.dao;

import com.database.aim.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Integer> {
    List<Post> findPostsByPoster(int posterId);
}
