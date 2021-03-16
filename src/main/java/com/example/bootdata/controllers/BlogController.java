package com.example.bootdata.controllers;

import com.example.bootdata.Response;
import com.example.bootdata.dto.PostDTO;
import com.example.bootdata.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BlogController {
    private final PostService postService;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Collection<PostDTO> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "blogMain";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blogAdd";
    }

    @ResponseBody
    @PostMapping("/blog/add")
    public Response<String> blogPostAdd(@RequestBody PostDTO postDTO) {
        postService.create(postDTO);
        return new Response<>("OK", "/blog");
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable("id") Long id, Model model) {
        try {
            PostDTO postDTO = postService.getPostById(id);
            postService.incViewsById(id);
            model.addAttribute("post", postDTO);
            return "blogDetails";
        } catch (NoSuchElementException e) {
            model.addAttribute("id", id);
            return "postNotFound";
        }
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") Long id, Model model) {
        try {
            PostDTO postDTO = postService.getPostById(id);
            model.addAttribute("post", postDTO);
            return "blogEdit";
        } catch (NoSuchElementException e) {
            model.addAttribute("id", id);
            return "postNotFound";
        }
    }

    @ResponseBody
    @PutMapping("/blog/{id}/edit")
    public Response<String> blogPostEdit(@RequestBody PostDTO postDTO) {
        postService.update(postDTO);
        return new Response<>("OK", "/blog");
    }

    @ResponseBody
    @DeleteMapping("/blog/{id}/remove")
    public Response<String> blogPostDelete(@RequestBody PostDTO postDTO) {
        postService.remove(postDTO.getId());
        return new Response<>("OK", "/blog");
    }
}
