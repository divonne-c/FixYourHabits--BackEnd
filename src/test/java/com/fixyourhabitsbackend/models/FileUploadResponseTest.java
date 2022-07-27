package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUploadResponseTest {

    @Test
    void getFileName() {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        fileUploadResponse.setFileName("photo");

        String result = fileUploadResponse.getFileName();

        assertEquals("photo", result);
    }

    @Test
    void getUrl() {
        FileUploadResponse fileUploadResponse = new FileUploadResponse("photo", "contenttype", "url");

        String result = fileUploadResponse.getUrl();

        assertEquals("url", result);
    }

    @Test
    void getContentType() {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        fileUploadResponse.setContentType("contenttype");

        String result = fileUploadResponse.getContentType();

        assertEquals("contenttype", result);
    }

}