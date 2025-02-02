package com.restdocspractice;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final Map<Long, Post> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Post save(Post post) {
        Long id = idGenerator.incrementAndGet();
        post.setId(id);
        database.put(id, post);
        return post;
    }

    public Post findById(Long id) {
        return database.get(id);
    }

    public List<Post> findAll() {
        return List.copyOf(database.values());
    }
}
