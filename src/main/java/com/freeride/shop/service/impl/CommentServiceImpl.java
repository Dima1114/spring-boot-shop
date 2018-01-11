package com.freeride.shop.service.impl;

import com.freeride.shop.dto.CommentDto;
import com.freeride.shop.entity.Comment;
import com.freeride.shop.entity.Item;
import com.freeride.shop.entity.User;
import com.freeride.shop.repository.CommentRepository;
import com.freeride.shop.service.CommentService;
import com.freeride.shop.service.ItemService;
import com.freeride.shop.service.SecurityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ItemService itemService;
    private SecurityService securityService;

    public CommentServiceImpl(CommentRepository commentRepository, ItemService itemService, SecurityService securityService) {
        this.commentRepository = commentRepository;
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @Override
    public List<Comment> getItemsComments(Item item) {
        return commentRepository.findAllByItemOrderByAddingDateDesc(item);
    }

    @Override
    public List<Comment> getRecentlyAdded() {
        return commentRepository.findTop5ByOrderByAddingDateDesc();
    }

    @Override
    public Page<Comment> getAllComments(int page) {
        Pageable pageable = new PageRequest(page - 1, 12, new Sort("addingDate"));
        return commentRepository.findAll(pageable);
    }

    @Override
    public void addComment(CommentDto commentDto) {
        Comment comment = new Comment();

        Item item = itemService.getItem(commentDto.getItemId());
        User user = securityService.getCurrentUser();

        comment.setItem(item);
        comment.setUser(user);
        comment.setText(commentDto.getText());
        comment.setAddingDate(LocalDateTime.now());

        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.delete(commentId);
    }
}
