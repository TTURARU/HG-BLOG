package com.hg.hgblogback.service.implement;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hg.hgblogback.dto.request.posts.CreateCommentRequestDTO;
import com.hg.hgblogback.dto.request.posts.CreatePostsRequestDTO;
import com.hg.hgblogback.dto.request.posts.EditPostsRequestDTO;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.dto.response.posts.CreateCommentResponseDTO;
import com.hg.hgblogback.dto.response.posts.CreatePostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.DeleteCommentResponseDTO;
import com.hg.hgblogback.dto.response.posts.DeletePostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.DetailPostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.EditPostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetCommentListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetFavoriteListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetLatestPostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetSearchPostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetTop3PostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetUserPostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.IncreaseViewCountResponseDTO;
import com.hg.hgblogback.dto.response.posts.PutFavoriteResponseDTO;
import com.hg.hgblogback.entity.CommentEntity;
import com.hg.hgblogback.entity.FavoriteEntity;
import com.hg.hgblogback.entity.ImageEntity;
import com.hg.hgblogback.entity.PostsEntity;
import com.hg.hgblogback.entity.PostsListViewEntity;
import com.hg.hgblogback.entity.SearchLogEntity;
import com.hg.hgblogback.repository.CommentRepository;
import com.hg.hgblogback.repository.FavoriteRepository;
import com.hg.hgblogback.repository.ImageRepository;
import com.hg.hgblogback.repository.PostsListViewRepository;
import com.hg.hgblogback.repository.PostsRepository;
import com.hg.hgblogback.repository.SearchLogRepository;
import com.hg.hgblogback.repository.UserRepository;
import com.hg.hgblogback.repository.resultSet.GetCommentListResultSet;
import com.hg.hgblogback.repository.resultSet.GetFavoriteListResultSet;
import com.hg.hgblogback.repository.resultSet.GetPostsResultSet;
import com.hg.hgblogback.service.PostsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    private final PostsListViewRepository postsListViewRepository;
    private final SearchLogRepository searchLogRepository;
    
    //# 게시물 작성
    @Override
    public ResponseEntity<? super CreatePostsResponseDTO> createPosts(CreatePostsRequestDTO dto, String email) {
        try {
            boolean existedEmail = userRepository.existsByEmail(email);
            if(!existedEmail) return CreatePostsResponseDTO.nonExistentUser();

            PostsEntity postsEntity = new PostsEntity(dto, email);
            postsRepository.save(postsEntity);

            int postsNumber = postsEntity.getPostsNumber();

            List<String> postsImageList = dto.getPostsImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image: postsImageList) {
                ImageEntity imageEntity = new ImageEntity(postsNumber, image);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return CreatePostsResponseDTO.success();
    }//* createPosts */

    //# 게시물 상세
    @Override
    public ResponseEntity<? super DetailPostsResponseDTO> detailPosts(Integer postsNumber) {
        GetPostsResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();
        try {
            resultSet = postsRepository.getPosts(postsNumber);
            if(resultSet == null) return DetailPostsResponseDTO.nonExistentPosts();

            imageEntities = imageRepository.findByPostsNumber(postsNumber);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return DetailPostsResponseDTO.success(resultSet, imageEntities);
    } //* detailPosts */ 

    //# 게시물 수정
    @Override
    public ResponseEntity<? super EditPostsResponseDTO> editPosts(EditPostsRequestDTO dto, Integer postsNumber,
            String email) {
        try {
            PostsEntity postsEntity = postsRepository.findByPostsNumber(postsNumber);
            if(postsEntity == null) return EditPostsResponseDTO.nonExistentPosts();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return EditPostsResponseDTO.nonExistentUser();

            String writerEmail = postsEntity.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return EditPostsResponseDTO.noPermission();

            postsEntity.editPosts(dto);
            postsRepository.save(postsEntity);

            imageRepository.deleteByPostsNumber(postsNumber);
            List<String> postsImageList = dto.getPostsImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image: postsImageList) {
                ImageEntity imageEntity = new ImageEntity(postsNumber, image);
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return EditPostsResponseDTO.success();
    } //* editPosts */

    //# 게시물 삭제
    @Override
    public ResponseEntity<? super DeletePostsResponseDTO> deletePosts(Integer postsNumber, String email) {
        try {
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return DeletePostsResponseDTO.nonExistentUser();

            PostsEntity postsEntity = postsRepository.findByPostsNumber(postsNumber);
            if(postsEntity == null) return DeletePostsResponseDTO.nonExistentPosts();

            String writerEmail = postsEntity.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return DeletePostsResponseDTO.noPermission();

            imageRepository.deleteByPostsNumber(postsNumber);
            commentRepository.deleteByPostsNumber(postsNumber);
            favoriteRepository.deleteByPostsNumber(postsNumber);
            postsRepository.delete(postsEntity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return DeletePostsResponseDTO.success();
    } //* deletePosts */

    //# 좋아요 누르기
    @Override
    public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer postsNumber, String email) {
        try {
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PutFavoriteResponseDTO.nonExistentUser();

            PostsEntity postsEntity = postsRepository.findByPostsNumber(postsNumber);
            if(postsEntity == null) return PutFavoriteResponseDTO.nonExistentPosts();

            FavoriteEntity favoriteEntity = favoriteRepository.findByPostsNumberAndUserEmail(postsNumber, email);
            if(favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(email, postsNumber);
                favoriteRepository.save(favoriteEntity);
                postsEntity.increaseFavoriteCount();
            } else {
                favoriteRepository.delete(favoriteEntity);
                postsEntity.decreaseFavoriteCount();
            }
            postsRepository.save(postsEntity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return PutFavoriteResponseDTO.success();
    } //* putFavorite */

    //# 좋아요 리스트
    @Override
    public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer postsNumber) {
        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();
        try {
            boolean existedPosts = postsRepository.existsByPostsNumber(postsNumber);
            if(!existedPosts) return GetFavoriteListResponseDTO.nonExistentPosts();
 
            resultSets = favoriteRepository.getFavoriteList(postsNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetFavoriteListResponseDTO.success(resultSets);
    } //* getFavoriteList */

    //# 댓글 작성
    @Override
    public ResponseEntity<? super CreateCommentResponseDTO> createComment(CreateCommentRequestDTO dto,
            Integer postsNumber, String email) {
        try {
            PostsEntity postsEntity = postsRepository.findByPostsNumber(postsNumber);
            if(postsEntity == null) return CreateCommentResponseDTO.nonExistentPosts();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return CreateCommentResponseDTO.nonExistentUser();

            CommentEntity commentEntity = new CommentEntity(dto, postsNumber, email);
            commentRepository.save(commentEntity);

            postsEntity.increaseCommentCount();
            postsRepository.save(postsEntity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return CreateCommentResponseDTO.success();
    } //* createComment */

    //# 댓글 리스트
    @Override
    public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer postsNumber) {
        List<GetCommentListResultSet> resultSets = new ArrayList<>();
        try {
            boolean existedPosts = postsRepository.existsByPostsNumber(postsNumber);
            if(!existedPosts) return GetCommentListResponseDTO.nonExistentPosts();

            resultSets = commentRepository.getCommentList(postsNumber);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetCommentListResponseDTO.success(resultSets);
    } //* getCommentList */

    //# 댓글 삭제
    @Override
    public ResponseEntity<? super DeleteCommentResponseDTO> deleteComment(Integer commentNumber, String email) {
        try {
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return DeleteCommentResponseDTO.nonExistentUser();

            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if(commentEntity == null) return DeleteCommentResponseDTO.nonExistentComment();

            String writerEmail = commentEntity.getUserEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return DeleteCommentResponseDTO.noPermission();

            commentRepository.deleteByCommentNumber(commentNumber);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return DeleteCommentResponseDTO.success();
    } //* deleteComment */

    //# 조회수 증가
    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(Integer postsNumber) {
        try {
            PostsEntity postsEntity = postsRepository.findByPostsNumber(postsNumber);
            if(postsEntity == null) return IncreaseViewCountResponseDTO.nonExistentPosts();

            postsEntity.increaseViewCount();
            postsRepository.save(postsEntity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return IncreaseViewCountResponseDTO.success();
    } //* increaseViewCount */

    //# 최신 게시물 리스트
    @Override
    public ResponseEntity<? super GetLatestPostsListResponseDTO> getLatestPostsList() {
        List<PostsListViewEntity> postsListViewEntities = new ArrayList<>();

        try {
            postsListViewEntities = postsListViewRepository.findByOrderByWriteDatetimeDesc();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetLatestPostsListResponseDTO.success(postsListViewEntities);
    } //* getLatestPostsList */

    //# 탑3 게시물 리스트
    @Override
    public ResponseEntity<? super GetTop3PostsListResponseDTO> getTop3PostsList() {
        List<PostsListViewEntity> postsListViewEntities = new ArrayList<>();
        try {
            Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sevenDaysAgo = simpleDateFormat.format(beforeWeek);
            postsListViewEntities = postsListViewRepository.findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(sevenDaysAgo);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetTop3PostsListResponseDTO.success(postsListViewEntities);
    } //* getTop3PostsList */

    //# 검색 게시물 리스트
    @Override
    public ResponseEntity<? super GetSearchPostsListResponseDTO> getSearchPostsList(String searchWord,
            String preSearchWord) {
        List<PostsListViewEntity> postsListViewEntities = new ArrayList<>();
        try {
            postsListViewEntities = postsListViewRepository.findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(searchWord, searchWord);
            
            SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, preSearchWord, false);
            searchLogRepository.save(searchLogEntity);

            boolean relation = preSearchWord != null;
            if(relation) {
                searchLogEntity = new SearchLogEntity(preSearchWord, searchWord, relation);
                searchLogRepository.save(searchLogEntity);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetSearchPostsListResponseDTO.success(postsListViewEntities);
    } //* getSearchPostsList */

    
    //# 유저 게시물 리스트
    @Override
    public ResponseEntity<? super GetUserPostsListResponseDTO> getUserPostsList(String email) {
        List<PostsListViewEntity> postsListViewEntities = new ArrayList<>();
        try {
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return GetUserPostsListResponseDTO.nonExistentUser();

            postsListViewEntities = postsListViewRepository.findByWriterEmailOrderByWriteDatetimeDesc(email);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetUserPostsListResponseDTO.success(postsListViewEntities);
    } //* getUserPostsList */

    
    
}
