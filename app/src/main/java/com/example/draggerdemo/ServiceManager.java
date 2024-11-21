package com.example.draggerdemo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServiceManager {
    private CommentClient mCommentClient;

    @Inject
    public ServiceManager(CommentClient commentClient) {
        mCommentClient = commentClient;
    }

    public CommentClient getCommentClient() {
        return mCommentClient;
    }
}
