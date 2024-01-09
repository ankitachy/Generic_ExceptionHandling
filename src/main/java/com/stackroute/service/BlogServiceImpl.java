package com.stackroute.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/* This is ServiceImplementation Class which should implement BlogService Interface and override all the implemented methods.
 * Handle suitable exceptions for all the implemented methods*/

import com.stackroute.domain.Blog;
import com.stackroute.exception.BlogAlreadyExistsException;
import com.stackroute.exception.BlogNotFoundException;
import com.stackroute.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {
	 private BlogRepository blogRepository;

	    /**
	     * Constructor based Dependency injection to inject BlogRepository here
	     */
	    @Autowired
	    public BlogServiceImpl(BlogRepository blogRepository) {
	        this.blogRepository = blogRepository;
	    }

	@Override
	public Blog saveBlog(Blog blog) throws BlogAlreadyExistsException {
		Blog blogDetails=new Blog();
		blogDetails.setBlogId(blog.getBlogId());
		blogDetails.setBlogTitle(blog.getBlogTitle());
		blogDetails.setAuthorName(blog.getAuthorName());
		blogDetails.setBlogContent(blog.getBlogContent());
		blogRepository.save(blogDetails);
		return blogDetails;
	}

	@Override
	public List<Blog> getAllBlogs() throws Exception {
		Iterable<Blog> bloggerList = blogRepository.findAll();
		List<Blog> blog = new ArrayList<>();
		bloggerList.forEach(x -> {
			Blog b = new Blog();
			b.setBlogId(x.getBlogId());
			b.setBlogTitle(x.getBlogTitle());
			b.setAuthorName(x.getAuthorName());
			b.setBlogContent(x.getBlogContent());
			blog.add(b);
		});
		
		return blog;
	}

	@Override
	public Blog getBlogById(int id) throws BlogNotFoundException {
		Optional<Blog> option =blogRepository.findById(id);
		Blog blog =option.orElseThrow(()->new BlogNotFoundException("Blog Not Found"));
		return blog;
	}

	@Override
	public Blog deleteBlog(int id) throws BlogNotFoundException{
		Optional<Blog> option=blogRepository.findById(id);
		Blog blog =option.orElseThrow(()->new BlogNotFoundException("Blog Not Found"));
		 blogRepository.delete(blog);	
		return blog;
	}

	@Override
	public Blog updateBlog(Blog blog) throws BlogNotFoundException, BlogAlreadyExistsException {
		Optional<Blog> option =blogRepository.findById(blog.getBlogId());
		Blog b=option.orElseThrow(()->new BlogNotFoundException("Blog Not Found"));
		if(b==blog) {
			throw new BlogAlreadyExistsException("Blog Already Exists");
		}
		b.setBlogId(blog.getBlogId());
		b.setBlogTitle(blog.getBlogTitle());
		b.setAuthorName(blog.getAuthorName());
		b.setBlogContent(blog.getBlogContent());
		return blogRepository.save(b);


	}

}

