package com.hg.hgblogback.repository.resultSet;

public interface GetPostsResultSet {
    Integer getPostsNumber();
    String getTitle();
    String getContent();
    String getWriteDatetime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();
    Integer getViewCount();
}
