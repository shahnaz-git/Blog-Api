package com.ecom.blogApi.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.blogApi.api.model.BlogCategory;
import com.ecom.blogApi.api.model.BlogCategoryImage;
import com.ecom.blogApi.datamodel.BlogCategoryData;
import com.ecom.blogApi.datamodel.BlogCategoryImageData;
import com.ecom.blogApi.repository.BlogCategoryImageRepository;
import com.ecom.blogApi.repository.BlogCategoryRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class BlogCategoryService {

	BlogCategoryData blogCategoryData;
	BlogCategoryImageData blogCategoryImageData;

	List<BlogCategoryData> blogCategoryDataList;

	Optional<BlogCategoryData> blogCategoryDataOptional;
	Optional<BlogCategoryImageData> blogCategoryImageDataOptional;

	@Autowired
	BlogCategoryRepository blogCategoryRepository;

	@Autowired
	BlogCategoryImageRepository blogCategoryImageRepository;

	public BlogCategory createBlogCategory(String categoryName, String seoTitle, String seoMeta, MultipartFile imgData)
			throws Exception {

		blogCategoryData = new BlogCategoryData();

		String imageName = "";

		String projectId = "achievers-one";
		String bucketName = "files.nxgecom.in";
//		String filePath = "D:\\OfficeWork\\BlogApi\\Blog-Api\\Files\\demoImage.png";

		StringBuffer imgExtention = new StringBuffer(".");
		try {

			String contentType = imgData.getContentType();
			imgExtention.append(contentType.substring(contentType.lastIndexOf("/") + 1));

		} catch (Exception ex) {
		}

		imageName = categoryName + "_Category" + imgExtention;
		System.out.println("image name ====== " + imageName);
		String objectName = "devecomm/" + imageName;

		try {

			blogCategoryData.setCategoryName(categoryName);
			blogCategoryData.setSeoTitle(seoTitle);
			blogCategoryData.setSeoMetaDesc(seoMeta);
			blogCategoryData.setStatus("Active");

			List<BlogCategoryImageData> blgCategoryImgData = new ArrayList<BlogCategoryImageData>();
			BlogCategoryImageData categoryImage = new BlogCategoryImageData();

			categoryImage.setCategoryImageName(imageName);
//			String imgDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(imageName).toUriString();
			String imgDownloadUri = "https://" + bucketName + "/" + objectName;
			System.out.println("imgDownloadUri  ========  " + imgDownloadUri);

			Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
			BlobId blobId = BlobId.of(bucketName, objectName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//			storage.createFrom(blobInfo, Paths.get(filePath));
			byte[] content = imgData.getBytes();
			storage.createFrom(blobInfo, new ByteArrayInputStream(content));

			categoryImage.setCategoryImageUrl(imgDownloadUri);
			categoryImage.setBlogCategoryData(blogCategoryData);

			blgCategoryImgData.add(categoryImage);

			blogCategoryData.setBlogCategoryImageData(blgCategoryImgData);
			blogCategoryData = blogCategoryRepository.save(blogCategoryData);

			BlogCategory blgCategory = new BlogCategory();
			blgCategory.setBlogcategoryId(blogCategoryData.getBlogCategoryId());

			return blgCategory;

		} catch (Exception e) {

			throw new Exception("unable to save data" + e.getMessage());
		}
	}

	public List<BlogCategory> getAllBlogCategory() throws Exception {
		try {
			List<BlogCategory> blogCategory = new ArrayList<BlogCategory>();
			blogCategoryDataList = blogCategoryRepository.findByStatus("Active");

			if (blogCategoryDataList.size() > 0) {
				for (BlogCategoryData blogCateData : blogCategoryDataList) {
					BlogCategory bcategory = new BlogCategory();

					bcategory.setBlogCategoryName(blogCateData.getCategoryName());
					bcategory.setSeoMetaDescription(blogCateData.getSeoMetaDesc());
					bcategory.setSeoTitle(blogCateData.getSeoTitle());
					bcategory.setBlogcategoryId(blogCateData.getBlogCategoryId());
					bcategory.setStatus(blogCateData.getStatus());

					BlogCategoryImage blogCategoryImage = new BlogCategoryImage();

					List<BlogCategoryImageData> blogCategoryImageData = blogCateData.getBlogCategoryImageData();
					for (BlogCategoryImageData blgImgData : blogCategoryImageData) {

						blogCategoryImage.setBlogCategoryImageId(blgImgData.getBlogImagesId());
						blogCategoryImage.setCategoryImageName(blgImgData.getCategoryImageName());
						System.out.println("image name ====== " + blgImgData.getCategoryImageName());
						blogCategoryImage.setCategoryImageDownloadUrl(blgImgData.getCategoryImageUrl());

					}

					bcategory.setCategoryImage(blogCategoryImage);

					blogCategory.add(bcategory);

				}
			}

			return blogCategory;
		} catch (Exception v) {
			throw new Exception("Data not found");
		}
	}

	public BlogCategory getSingleBlogCategory(int blogCategoryId) throws Exception {

		blogCategoryDataOptional = blogCategoryRepository.findById(blogCategoryId);

		if (blogCategoryDataOptional.isPresent()) {
			blogCategoryData = blogCategoryDataOptional.get();
			BlogCategory blogCate = new BlogCategory();
			blogCate.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
			blogCate.setBlogCategoryName(blogCategoryData.getCategoryName());
			blogCate.setSeoMetaDescription(blogCategoryData.getSeoMetaDesc());
			blogCate.setSeoTitle(blogCategoryData.getSeoTitle());
			blogCate.setStatus(blogCategoryData.getStatus());

			BlogCategoryImage blgCategoryImage = new BlogCategoryImage();
			List<BlogCategoryImageData> categoryImgData = blogCategoryData.getBlogCategoryImageData();
			for (BlogCategoryImageData categoryImageData : categoryImgData) {
				blgCategoryImage.setBlogCategoryImageId(categoryImageData.getBlogImagesId());
				blgCategoryImage.setCategoryImageName(categoryImageData.getCategoryImageName());
				System.out.println("image Name ======= " + categoryImageData.getCategoryImageName());
				blgCategoryImage.setCategoryImageDownloadUrl(categoryImageData.getCategoryImageUrl());
				System.out.println("image download Url ======= " + categoryImageData.getCategoryImageUrl());
			}

			blogCate.setCategoryImage(blgCategoryImage);

			return blogCate;
		} else {
			throw new Exception("Data not found!");
		}
	}

	public BlogCategory updateBlogCategory(int id, BlogCategory blogCate) {

		BlogCategory blgCate;

		blogCategoryDataOptional = blogCategoryRepository.findById(id);
		if (blogCategoryDataOptional.isPresent()) {

			blogCategoryData = blogCategoryDataOptional.get();

			blogCategoryData.setCategoryName(blogCate.getBlogCategoryName());
			blogCategoryData.setSeoMetaDesc(blogCate.getSeoMetaDescription());
			blogCategoryData.setSeoTitle(blogCate.getSeoTitle());
			blogCategoryData.setStatus(blogCate.getStatus());
			
			BlogCategoryImage blgCategoryImage = blogCate.getCategoryImage();
			List<BlogCategoryImageData> blgCategoryImgData = blogCategoryData.getBlogCategoryImageData();
			for(BlogCategoryImageData blgCateImgData : blgCategoryImgData) {
				String name = blgCateImgData.getCategoryImageName();
				System.out.println("name ==== "+name);
				blgCateImgData.setCategoryImageName(blgCategoryImage.getCategoryImageName());
			}
		}

		blogCategoryRepository.save(blogCategoryData);

		blgCate = new BlogCategory();
		blgCate.setBlogcategoryId(blogCategoryData.getBlogCategoryId());

		return blgCate;

	}
	
	public BlogCategory updateCategoryImage(int id,String categoryName, String seoTitle, String seoMeta , String status, MultipartFile categoryImg) throws IOException {

		BlogCategory blgCate;
		
		String categoryImageName = "";
		String projectId = "achievers-one";
		String bucketName = "files.nxgecom.in";
		StringBuffer imgExtention = new StringBuffer(".");
		
		try {
			String contentType = categoryImg.getContentType();
			imgExtention.append(contentType.substring(contentType.lastIndexOf("/") + 1));
		} catch (Exception ex) {
		}

		categoryImageName = categoryName + "_Category" + imgExtention;
		System.out.println("image name ====== " + categoryImageName);
		String objectName = "devecomm/" + categoryImageName;

		blogCategoryDataOptional = blogCategoryRepository.findById(id);
		if (blogCategoryDataOptional.isPresent()) {

			blogCategoryData = blogCategoryDataOptional.get();

			blogCategoryData.setCategoryName(categoryName);
			blogCategoryData.setSeoMetaDesc(seoMeta);
			blogCategoryData.setSeoTitle(seoTitle);
			blogCategoryData.setStatus(status);
			
//			BlogCategoryImage blgCategoryImage = blogCate.getCategoryImage();
			List<BlogCategoryImageData> blgCategoryImgData = blogCategoryData.getBlogCategoryImageData();
			for(BlogCategoryImageData blgCateImgData : blgCategoryImgData) {
				
				System.out.println("before update img name === "+blgCateImgData.getCategoryImageName());
				blgCateImgData.setCategoryImageName(categoryImageName);
				
				Storage strg = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
				BlobId blbId = BlobId.of(bucketName, objectName);
				BlobInfo info = BlobInfo.newBuilder(blbId).build();
				byte[] imgdata = categoryImg.getBytes();
				strg.createFrom(info , new ByteArrayInputStream(imgdata));
				
				String imgDownloadUri = "https://" + bucketName + "/" + objectName;
				blgCateImgData.setCategoryImageUrl(imgDownloadUri);
				
//				blgCateImgData.setCategoryImageName(blgCategoryImage.getCategoryImageName());
			}
		}

		blogCategoryRepository.save(blogCategoryData);

		blgCate = new BlogCategory();
		blgCate.setBlogcategoryId(blogCategoryData.getBlogCategoryId());

		return blgCate;

	}

	public BlogCategory deleteBlogCategory(int blogcategoryId) {

		BlogCategory bb;
		blogCategoryDataOptional = blogCategoryRepository.findById(blogcategoryId);

		if (blogCategoryDataOptional.isPresent()) {

			blogCategoryData = blogCategoryDataOptional.get();

			blogCategoryData.setStatus("Inactive");

		}

		blogCategoryRepository.save(blogCategoryData);

		bb = new BlogCategory();
		bb.setBlogcategoryId(blogCategoryData.getBlogCategoryId());

		return bb;

	}

}
