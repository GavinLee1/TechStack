package com.lgz.springBoot.controller;

import com.lgz.springBoot.service.http.OkHttpService;
import com.lgz.util.FileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;

/**
 * Created by ligaozhao on 06/10/17.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Inject
    private OkHttpService okHttpService;

    @RequestMapping(value = "/post_file", method = RequestMethod.POST)
    public Callable<String> testPostFile(
            @RequestParam MultipartFile file) throws Exception {
        return () -> {
            String response = okHttpService.postWithFile(file);
            return response;
        };
    }

    @RequestMapping(value = "/receive_file", method = RequestMethod.POST)
    public ResponseEntity testReceiveFile(
            @RequestParam MultipartFile file,
            HttpServletRequest request) throws Exception {
        System.out.println("----length " + file.getBytes().length);
        System.out.println("----getContentType " + file.getContentType());
        System.out.println("----getOriginalFilename " + file.getOriginalFilename());
        System.out.println("----getName " + file.getName());
        System.out.println("----getSize " + file.getSize());
        file.getInputStream();

        String baseFolderName = FileUtil.getFolder();
        File receivedFile = new File(baseFolderName + "receive" + ".png");

        FileOutputStream out = new FileOutputStream(receivedFile);
        out.write(file.getBytes());

        return ResponseEntity.ok().build();
    }
}
