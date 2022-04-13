package com.yst.entity;

/**
 * @author Yan Siting
 */
public class HttpResponse {

    /**
     * http response status line
     */
    private String statusLine;

    /**
     * content-type in http response header
     */
    private String contentType;

    /**
     * response body
     */
    private Object responseBody;

    public HttpResponse() {
    }

    public HttpResponse(String statusLine, String contentType, Object responseBody) {
        this.statusLine = statusLine;
        this.contentType = contentType;
        this.responseBody = responseBody;
    }

    public HttpResponse(Object responseBody) {
        this.responseBody = responseBody;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusLine='" + statusLine + '\'' +
                ", contentType='" + contentType + '\'' +
                ", responseBody=" + responseBody +
                '}';
    }
}
