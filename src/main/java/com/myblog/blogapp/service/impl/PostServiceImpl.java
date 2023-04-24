package com.myblog.blogapp.service.impl;

import com.myblog.blogapp.entities.Post;
import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.repository.PostRepository;
import com.myblog.blogapp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post postEntity = postRepo.save(post);
        PostDto dto = mapToDto(postEntity);
        return dto;
    }



    private PostDto mapToDto(Post postEntity) {
        PostDto dto = new PostDto();
        dto.setId(postEntity.getId());
        dto.setTitle(postEntity.getTitle());
        dto.setDescription(postEntity.getDescription());
        dto.setContent(postEntity.getContent());
        return dto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    @Override
    public List<PostDto> getAllPosts() {
      List<Post> posts =  postRepo.findAll();
       return posts.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(long id) {
       Optional<Post> byId = postRepo.findById(id);
       Post post =byId.get();
        return mapToDto(post);
    }

}
