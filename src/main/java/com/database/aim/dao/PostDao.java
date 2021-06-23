package com.database.aim.dao;

import com.database.aim.pojo.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PostDao extends JpaRepository<Post, Integer> {
    List<Post> findPostsByPoster(int posterId);

    @Query("select t from Post t")
    List<Post> findTop(Pageable pageable);
}
