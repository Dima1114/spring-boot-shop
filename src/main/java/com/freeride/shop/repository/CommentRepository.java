package com.freeride.shop.repository;

import com.freeride.shop.entity.Comment;
import com.freeride.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByItemOrderByAddingDateDesc(Item item);

    List<Comment> findTop5ByOrderByAddingDateDesc();
}
