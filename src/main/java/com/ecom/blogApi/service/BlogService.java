package com.ecom.blogApi.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.blogApi.api.model.Blog;
import com.ecom.blogApi.api.model.BlogImages;
import com.ecom.blogApi.datamodel.BlogData;
import com.ecom.blogApi.datamodel.BlogImageData;
import com.ecom.blogApi.repository.BlogImageRepository;
import com.ecom.blogApi.repository.BlogRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class BlogService {

	BlogData blogData;
	BlogImageData blogImageData;

	List<BlogData> blogDataList;

	Optional<BlogData> blgDataOptional;
	Optional<BlogImageData> blgImageDataOptional;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	BlogImageRepository blogImgRepository;

	public Blog createBlog(int categoryId,String authorName , String blogTitle , String description , String seoTitle ,String seoMetaDescription ,String status, MultipartFile imgData , MultipartFile bannerData) throws Exception {
		blogData = new BlogData();
		
		Blog blog;
		
		String imageName = "";
		String bannerName = "";

		String projectId = "achievers-one";
		String bucketName = "files.nxgecom.in";
		
		StringBuffer imgExtention = new StringBuffer(".");
		StringBuffer bannerExtention = new StringBuffer(".");
		
		try {

			String imageContentType = imgData.getContentType();
			imgExtention.append(imageContentType.substring(imageContentType.lastIndexOf("/") + 1));
			
			String bannerContentType = bannerData.getContentType();
			bannerExtention.append(bannerContentType.substring(bannerContentType.lastIndexOf("/") + 1));

		} catch (Exception ex) {
		}

		imageName = categoryId + "_blog_image" + imgExtention;
		bannerName = categoryId + "_blog_banner" + bannerExtention;
		System.out.println("image name ====== " + imageName);
		System.out.println("banner name ====== " + bannerName);
		String imgObjectName = "devecomm/" + imageName;
		String bannerObjectName = "devecomm/" + bannerName;

		
		try {
			blogData.setBlogCategoryId(categoryId);
			blogData.setAuthorName(authorName);
			blogData.setBlogTitle(blogTitle);
			blogData.setDescription(description);
			blogData.setSeoTitle(seoTitle);
			blogData.setSeoMetaDescription(seoMetaDescription);
			blogData.setStatus(status);
			
			List<BlogImageData> blogImageDataList = new ArrayList<BlogImageData>();
			
			BlogImageData blogImageData = new BlogImageData();
			blogImageData.setBlogImageName(imageName);
			String imgDownloadUri = "https://" + bucketName + "/" + imgObjectName;
			System.out.println("imgDownloadUri  ========  " + imgDownloadUri);

			Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
			BlobId blobId = BlobId.of(bucketName, imgObjectName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
			byte[] content = imgData.getBytes();
			storage.createFrom(blobInfo, new ByteArrayInputStream(content));
			
			blogImageData.setImageDownloadUrl(imgDownloadUri);
			blogImageData.setImgType("IMAGE");
			
			// Banner Image
			BlogImageData blogBannerData = new BlogImageData();
			blogBannerData.setBlogImageName(bannerName);
			
			String bannerDownloadUri = "https://" + bucketName + "/" + bannerObjectName;
			System.out.println("bannerDownloadUri  ========  " + bannerDownloadUri);
			
			BlobId blobId2 = BlobId.of(bucketName, bannerObjectName);
			BlobInfo blobInfo2 = BlobInfo.newBuilder(blobId2).build();
			byte[] content2 = bannerData.getBytes();
			storage.createFrom(blobInfo2, new ByteArrayInputStream(content2));
			
			blogBannerData.setImageDownloadUrl(bannerDownloadUri);
			blogBannerData.setImgType("Banner");
			
			blogImageDataList.add(blogImageData);
			blogImageDataList.add(blogBannerData);
			
			blogImageData.setBlogData(blogData);
			blogBannerData.setBlogData(blogData);
			
			blogData.setBlogImageData(blogImageDataList);
			
			blogData = blogRepository.save(blogData);
			
			blog = new Blog();
			blog.setBlogId(blogData.getBlogId());
			
			return blog;
		}catch(Exception ex) {
			throw new Exception("Unable to Save Data "+ ex.getMessage());
		}
	}
	

	public List<Blog> getAllBlogs() throws Exception {
			List<Blog> blogList = new ArrayList<Blog>();
			blogDataList = blogRepository.findByStatus("Active");

			if (blogDataList.size() > 0) {
				for (BlogData blgData : blogDataList) {
					Blog blgResponse = new Blog();
					blgResponse.setBlogId(blgData.getBlogId());
					blgResponse.setAuthorName(blgData.getAuthorName());
					System.out.println("author name == "+ blgData.getAuthorName());
					blgResponse.setBlogTitle(blgData.getBlogTitle());
					blgResponse.setDescription(blgData.getDescription());
					blgResponse.setSeoTitle(blgData.getSeoTitle());
					blgResponse.setSeoMetaDescription(blgData.getSeoMetaDescription());
					blgResponse.setStatus(blgData.getStatus());

					BlogImages subImage = new BlogImages();
					BlogImages bannerImage = new BlogImages();

					List<BlogImageData> blogImageDataList = blgData.getBlogImageData();
					for (BlogImageData blgImageData : blogImageDataList) {
						System.out.println("nnn = "+blgImageData.getBlogImageName());
						if ("IMAGE".equals(blgImageData.getImgType())) {
							subImage.setBlogImageId(blgImageData.getBlogImageId());
							subImage.setBlogImageName(blgImageData.getBlogImageName());
							System.out.println("image name === "+blgImageData.getBlogImageName());
							subImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
							subImage.setImageType(blgImageData.getImgType());
						} else {
							bannerImage.setBlogImageId(blgImageData.getBlogImageId());
							bannerImage.setBlogImageName(blgImageData.getBlogImageName());
							System.out.println("banner name === "+blgImageData.getBlogImageName());
							bannerImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
							bannerImage.setImageType(blgImageData.getImgType());
						}
					}
					blgResponse.setBlogSubImage(subImage);
					blgResponse.setBlogBannerImage(bannerImage);
					blogList.add(blgResponse);
				}
				return blogList;
			}else {
				
				throw new Exception("DATA NOT FOUND !");
			}

	}
	
	public Blog getSingleBlog(int blogId) throws Exception {
		
		blgDataOptional = blogRepository.findById(blogId);

		if (blgDataOptional.isPresent()) {
			blogData = blgDataOptional.get();
			Blog blogResponse = new Blog();
			blogResponse.setBlogId(blogData.getBlogId());
			blogResponse.setAuthorName(blogData.getAuthorName());
			blogResponse.setBlogTitle(blogData.getBlogTitle());
			blogResponse.setDescription(blogData.getDescription());
			blogResponse.setSeoTitle(blogData.getSeoTitle());
			blogResponse.setSeoMetaDescription(blogData.getSeoMetaDescription());
			blogResponse.setStatus(blogData.getStatus());

			BlogImages subImage = new BlogImages();
			BlogImages bannerImage = new BlogImages();

			List<BlogImageData> blogImageDataList = blogData.getBlogImageData();
			for (BlogImageData blgImageData : blogImageDataList) {
				if("IMAGE".equals(blgImageData.getImgType())) { 
					subImage.setBlogImageId(blgImageData.getBlogImageId());
					subImage.setBlogImageName(blgImageData.getBlogImageName());
					subImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					subImage.setImageType(blgImageData.getImgType());

					
				}else {
					bannerImage.setBlogImageId(blgImageData.getBlogImageId());
					bannerImage.setBlogImageName(blgImageData.getBlogImageName());
					bannerImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					bannerImage.setImageType(blgImageData.getImgType());
				}
			}
			blogResponse.setBlogSubImage(subImage);
			blogResponse.setBlogBannerImage(bannerImage);

			return blogResponse;
		} else {
			throw new Exception("Data not found!");
		}
	}

}
