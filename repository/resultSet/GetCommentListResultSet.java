package com.hg.hgblogback.repository.resultSet;

public interface GetCommentListResultSet {
    String getNickname();
    String getProfileImage();
    String getWriteDatetime();
    String getContent();
    Integer getCommentNumber();
}
