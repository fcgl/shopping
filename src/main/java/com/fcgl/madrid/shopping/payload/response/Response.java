package com.fcgl.madrid.shopping.payload.response;

import java.time.Instant;

public class Response<T> {
    private T response;
    private InternalStatus status;
    private Long timestamp;

    public Response(InternalStatus status, T response) {
        this.status = status;
        this.response = response;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public InternalStatus getStatus() {
        return status;
    }

    public void setStatus(InternalStatus status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
