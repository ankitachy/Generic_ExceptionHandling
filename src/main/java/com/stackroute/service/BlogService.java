package com.stackroute.service;
import com.stackroute.domain.Blog;
import com.stackroute.exception.BlogAlreadyExistsException;
import com.stackroute.exception.BlogNotFoundException;

import java.util.List;

/* Throw suitable exceptions for all methods. Also handle Database Connectivity Failure incase your database connectivity fails,it should throw suitable exception *

 */
public interface BlogService {
     /**
      * AbstractMethod to save a blog
      */
     Blog saveBlog(Blog blog) throws BlogAlreadyExistsException ;
     /**
      * AbstractMethod to get all blogs
      */
     List<Blog> getAllBlogs() throws Exception;
     /**
      * AbstractMethod to get blog by id
      */
     Blog getBlogById(int id)throws BlogNotFoundException ;
     /**
      * AbstractMethod to delete blog by id
      */
     Blog deleteBlog(int id)throws BlogNotFoundException;
     /**
      * AbstractMethod to update a blog
     * @throws BlogAlreadyExistsException 
      */
     Blog updateBlog(Blog blog)throws BlogNotFoundException, BlogAlreadyExistsException ;
}

