package com.greenway.aws.s3.service;

import org.springframework.web.multipart.MultipartFile;


public interface AWSS3Service {
	
	String uploadFile(final MultipartFile multipartFile);

	String deleteFileFromS3Bucket(String fileUrl);

}
