package com.agan.repository;

import com.agan.exception.NotFoundException;
import com.agan.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong currentId = new AtomicLong(0);

  public List<Post> all() {
    return posts.values().stream()
            .filter(post -> !post.isRemoved())
            .collect(Collectors.toList());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id))
            .filter(post -> !post.isRemoved());
    }

  public Post save(Post post) {
    return posts.computeIfPresent(post.getId(), (id, existingPost) -> {
      if (existingPost.isRemoved()) {
        throw new NotFoundException("Post with id " + id + " is marked as removed and cannot be updated.");
      }
      existingPost.setContent(post.getContent());
      return existingPost;
    });
  }

  public void removeById(long id) {
    posts.computeIfPresent(id, (key, post) -> {
      post.setRemoved(true);
      return post;
    });
  }
}
