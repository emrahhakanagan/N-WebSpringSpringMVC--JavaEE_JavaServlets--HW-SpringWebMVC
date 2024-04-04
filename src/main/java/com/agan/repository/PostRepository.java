package com.agan.repository;

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
    // TODO need to change here ...
    long id = post.getId();

    posts.compute(id, (currentId, currentPost) -> {
      if (post.getId() <= 0) {
        long idNewPost = this.currentId.getAndDecrement();
        post.setId(idNewPost);
        return post;
      } else {
        if (currentPost != null) {
          currentPost.setContent(post.getContent());
          return currentPost;
        } else {
          return post;
        }
      }
    });

    return post;
  }

  public void removeById(long id) {
    // TODO need to change here ...
    posts.remove(id);
  }
}
