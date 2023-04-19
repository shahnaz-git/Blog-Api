package com.test.gcsfile.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.gax.paging.Page;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ImpersonatedCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class GcsFileService {

	private String credPath = "D:\\gcsFile\\gcsfile\\GcsKey\\achievers-one-1fdb21f92e43.json";
	
	public List<String> listOfFiles() throws FileNotFoundException, IOException {
		
		String bucketName = "files.nxgecom.in";

//		String credPath = "D:\\gcsFile\\gcsfile\\GcsFiles\\achievers-one-8bc53f14da25.json";
//		String credPath = "D:\\gcsFile\\gcsfile\\GcsKey\\achievers-one-1fdb21f92e43.json";

		ServiceAccountCredentials serviceCredentials = ServiceAccountCredentials
				.fromStream(new FileInputStream(credPath));
		serviceCredentials = (ServiceAccountCredentials) serviceCredentials
				.createScoped(Arrays.asList("https://www.googleapis.com/auth/iam"));

		ImpersonatedCredentials targetCredentials = ImpersonatedCredentials.create(serviceCredentials,
				"impersonated-account@project.iam.gserviceaccount.com", null,
				Arrays.asList("https://www.googleapis.com/auth/devstorage.read_only"), 300);

		Storage storage_service = StorageOptions.newBuilder().setProjectId("achievers-one")
				.setCredentials(targetCredentials).build().getService();

		List<String> list = new ArrayList<>();
		Page<Blob> blobs = storage_service.list(bucketName);
		for (Blob blob : blobs.iterateAll()) {
			System.out.println("blob == " + blob);
			list.add(blob.getName());
		}
		return list;
	}
	
	public void uploadFile(MultipartFile file) throws IOException {
		String bucketName = "files.nxgecom.in";
    	Credentials credentials = GoogleCredentials
    			  .fromStream(new FileInputStream(credPath));
    			Storage storage2 = StorageOptions.newBuilder().setCredentials(credentials)
    			  .setProjectId("achievers-one").build().getService();
    			
        BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).
                setContentType(file.getContentType()).build();
        Blob blob = storage2.create(blobInfo,file.getBytes());
    }
}









//com.google.api.client.http.HttpResponseException: 404 Not Found
//POST https://iamcredentials.googleapis.com/v1/projects/-/serviceAccounts/impersonated-account@project.iam.gserviceaccount.com:generateAccessToken
//{
//  "error": {
//    "code": 404,
//    "message": "Not found; Gaia id not found for email impersonated-account@project.iam.gserviceaccount.com",
//    "errors": [
//      {
//        "message": "Not found; Gaia id not found for email impersonated-account@project.iam.gserviceaccount.com",
//        "domain": "global",
//        "reason": "notFound"
//      }
//    ],
//    "status": "NOT_FOUND"
//  }
//}