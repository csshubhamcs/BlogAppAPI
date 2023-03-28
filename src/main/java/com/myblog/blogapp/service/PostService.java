package com.myblog.blogapp.service;

import com.myblog.blogapp.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
