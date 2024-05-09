package com.beanions.common.dto;

public class MyPageDTO {
  private String nickname;
  private int postCount;
  private int commentCount;
  private int totalCount;
  private int scheduleCount;

  private MembersDTO members;
  private PostDTO posts;
  private CommentsDTO comments;
  private SchedulesDTO schedules;

  public MyPageDTO(){}

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public int getPostCount() {
    return postCount;
  }

  public void setPostCount(int postCount) {
    this.postCount = postCount;
  }

  public int getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getScheduleCount() {
    return scheduleCount;
  }

  public void setScheduleCount(int scheduleCount) {
    this.scheduleCount = scheduleCount;
  }

  public MembersDTO getMembers() {
    return members;
  }

  public void setMembers(MembersDTO members) {
    this.members = members;
  }

  public PostDTO getPosts() {
    return posts;
  }

  public void setPosts(PostDTO posts) {
    this.posts = posts;
  }

  public CommentsDTO getComments() {
    return comments;
  }

  public void setComments(CommentsDTO comments) {
    this.comments = comments;
  }

  public SchedulesDTO getSchedules() {
    return schedules;
  }

  public void setSchedules(SchedulesDTO schedules) {
    this.schedules = schedules;
  }

  @Override
  public String toString() {
    return "MyPageDTO{" +
            "nickname='" + nickname + '\'' +
            ", postCount=" + postCount +
            ", commentCount=" + commentCount +
            ", totalCount=" + totalCount +
            ", scheduleCount=" + scheduleCount +
            ", members=" + members +
            ", posts=" + posts +
            ", comments=" + comments +
            ", schedules=" + schedules +
            '}';
  }
}
