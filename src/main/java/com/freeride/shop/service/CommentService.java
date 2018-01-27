package com.freeride.shop.service;

import com.freeride.shop.dto.CommentDto;
import com.freeride.shop.entity.Comment;
import com.freeride.shop.entity.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    List<Comment> getItemsComments(Item item);
    List<Comment> getRecentlyAdded();
    Page<Comment> getAllComments(int page);
    void addComment(CommentDto commentDto);

    //admin
    void deleteComment(Long commentId);
}
