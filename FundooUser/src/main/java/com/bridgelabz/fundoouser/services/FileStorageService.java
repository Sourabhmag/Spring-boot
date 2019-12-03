package com.bridgelabz.fundoouser.services;
/******************************************************************************
 *  Compilation:  javac -d bin FileStorageService.java
 *  Execution:    
 *      
 *  Purpose:  Used to provide services related to user profile pic
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   03-12-2019
 *
 ******************************************************************************/
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoouser.dto.FileStorageProperties;
import com.bridgelabz.fundoouser.exception.custom.FileNotFoundException;
import com.bridgelabz.fundoouser.exception.custom.FileStorageException;
@Service
public class FileStorageService {
	private final Path fileStorageLocation;

    /**
     * @Purpose - Used to set path of profile pic
     * @param fileStorageProperties
     */
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException(MessageReference.NO_DIRECTORY);
        }
    }

    /**
     * @Purpose - Used to store profile pic
     * @param file - Accepts file to be stored
     * @param email - Accepts username of user 
     * @return - Filename of pic
     */
    public String storeFile(MultipartFile file,String email) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileName=email+fileName;
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException(MessageReference.INVALID_PATH + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException(MessageReference.FILE_STORE_ERROR+" ==>" + fileName );
        }
    }
    
    /**
     * @Purpose - Used to retrive profile pic
     * @param profilePicPath
     * @return - Profile pic
     */
    public Resource loadFileAsResource(String profilePicPath) {
    	
    	try {
        	
            Path filePath = Paths.get(profilePicPath);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException(MessageReference.FILE_NOT_FOUND);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException(MessageReference.FILE_NOT_FOUND);
        }
    }
    
}
