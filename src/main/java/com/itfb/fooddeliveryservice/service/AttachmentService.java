package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.notification.Attachment;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AttachmentService {

    public Attachment createAttachment(String content, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(content);
        writer.close();
        Attachment attachment = new Attachment();
        attachment.setFileName(fileName);
        byte[] fileContent = FileUtils.readFileToByteArray(new File(fileName));
        attachment.setContent(Base64.getEncoder().encodeToString(fileContent));
        File file = new File(fileName);
        file.delete();
        return attachment;
    }
}
