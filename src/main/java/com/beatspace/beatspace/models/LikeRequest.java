package com.beatspace.beatspace.models;

public class LikeRequest {

    private Long resenhaId;
    private String userId;

    public LikeRequest(Long resenhaId , String userId){
        this.resenhaId = resenhaId;
        this.userId = userId;
    }

    public LikeRequest(){

    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setResenhaId(Long resenhaId) {
        this.resenhaId = resenhaId;
    }

    public Long getResenhaId() {
        return resenhaId;
    }

    public String getUserId() {
        return userId;
    }
}
