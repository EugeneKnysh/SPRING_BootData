package com.example.bootdata.services;

import com.example.bootdata.dto.PostDTO;
import com.example.bootdata.models.Post;
import com.example.bootdata.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Long create(PostDTO postDTO) {
        Post post = new Post();
        post.setAnons(postDTO.getAnons());
        post.setFullText(postDTO.getFullText());
        post.setTitle(postDTO.getTitle());

        post = postRepository.save(post);
        return post.getId();
    }

    public PostDTO getPostById(Long id) {
        return postRepository.findById(id)
                .map(this::getPostDTO)
                .orElseThrow(() -> new NoSuchElementException("Post " + id + " not found!"));
    }

    public Collection<PostDTO> getAll() {
        return postRepository.findAll().stream()
                .map(this::getPostDTO)
                .collect(Collectors.toList());
    }

    private PostDTO getPostDTO(Post p) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(p.getId());
        postDTO.setAnons(p.getAnons());
        postDTO.setFullText(p.getFullText());
        postDTO.setTitle(p.getTitle());
        postDTO.setViews(p.getViews());
        return postDTO;
    }

    public Long update(PostDTO postDTO) {
        Post post = postRepository.findById(postDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Post " + postDTO.getId() + " not found!"));
        post.setAnons(postDTO.getAnons());
        post.setFullText(postDTO.getFullText());
        post.setTitle(postDTO.getTitle());

        post = postRepository.save(post);
        return post.getId();
    }

    public boolean remove(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    public void incViewsById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Post " + id + " not found!"));
        int views = post.getViews() + 1;
        post.setViews(views);
        post = postRepository.save(post);
    }
}
