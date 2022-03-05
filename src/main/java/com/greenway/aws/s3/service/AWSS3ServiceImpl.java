package com.greenway.aws.s3.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AWSS3ServiceImpl implements AWSS3Service{

	private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3ServiceImpl.class);
	
	@Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${aws.s3.endpointUrl}")
    private String endpointUrl;
    
	@Override
	@Async
	public String uploadFile(MultipartFile multipartFile) {
		LOGGER.info("File upload in progress.");
		String fileUrl = "";
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            uploadFileToS3Bucket(fileName, file);
            fileUrl = endpointUrl + fileName;
            LOGGER.info("File upload is completed.");
            file.delete();  // To remove the file locally created in the project folder.
        } catch (final AmazonServiceException ex) {
            LOGGER.info("File upload has failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
        }
        return fileUrl;
	}

	private void uploadFileToS3Bucket(String fileName, File file) {
	        LOGGER.info("Uploading file with name= " + fileName);
	        ObjectMetadata test = new ObjectMetadata();
	        test.setContentType("image/jpeg");
	        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file).withMetadata(test).withCannedAcl(CannedAccessControlList.PublicRead);
	        amazonS3.putObject(putObjectRequest);
	}

	private File convertMultiPartFileToFile(MultipartFile multipartFile) {
		
		final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
	}
	
	private String generateFileName(MultipartFile multiPart) {
	    return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	@Override
	public String deleteFileFromS3Bucket(String fileUrl) {
		LOGGER.info("File delete in progress.");
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }

}
