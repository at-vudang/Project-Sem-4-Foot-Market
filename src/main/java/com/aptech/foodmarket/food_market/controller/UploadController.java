package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Unit;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Map;

/**
 * Created by KAI on 4/27/17.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/home/vudang/Documents/Sem4/Images/";

    @RequestMapping(value = "/upload",
            method = { RequestMethod.POST })
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        System.out.println("upload() called");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-type", "text/plain");
        if (file.isEmpty()) {
            request.setAttribute("message",
                    "Please select a file to upload");
            String message = "Please select a file to upload";
            return new ResponseEntity(message,responseHeaders,HttpStatus.BAD_REQUEST);
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER
                    + file.getOriginalFilename());
            if (Files.exists(path)){
                return new ResponseEntity(new String("File Exists"),responseHeaders,HttpStatus.NOT_FOUND);
            }
            Files.write(path, bytes);

            request.setAttribute("message",
                    "You have successfully uploaded '"
                            + file.getOriginalFilename() + "'");
            return new ResponseEntity(file.getOriginalFilename(),HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/remove",
            method = { RequestMethod.GET })
    public ResponseEntity<String> remove(
            @RequestParam("filename") String filename) {
        Path path = Paths.get(UPLOADED_FOLDER
                + filename);
        try {
            Files.delete(path);
            return new ResponseEntity("Detele file success!",HttpStatus.OK);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
