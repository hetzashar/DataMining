package com.dataming.fileuploaddownload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;


public class DownloadObjectSingleOperation {
	private static String bucketName     = "sambutrialbucket";
	private static String keyName        = "input.txt";
	private static String uploadFileName = "/home/sambu/input.txt";
	
	public static void main(String[] args) throws IOException {
        AmazonS3 s3client = new AmazonS3Client();
        try {
            System.out.println("Downloading a new object to S3 from a file\n");
            File file = new File(uploadFileName);
            S3Object object=s3client.getObject(new GetObjectRequest(bucketName, keyName));
            InputStream objectData = object.getObjectContent();
            objectData.close();
         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}