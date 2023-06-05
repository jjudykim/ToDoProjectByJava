package com.jjudykim.todoprojectbyjava;

public class User {

    private String id;
    private String password;
    private String email;
    private String nickname;
    private String team;
    private int iconCode;
    private boolean isLeader;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String id, String password, String email, String nickname, String team) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.team = team;
        this.isLeader = false;
        this.iconCode = 0;
    }

    // Name(get/set)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Password(get/set)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // email(get/set)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // nickname(get/set)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // team(get/set)
   public String getTeam() {
        return team;
   }

   public void setTeam(String team) {
       this.team = team;
   }

   // iconCode(get/set)
    public int getIconCode() {
        return iconCode;
    }

    public void setIconCode(int iconCode) {
        this.iconCode = iconCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickname + '\'' +
                ", team='" + team + '\'' +
                ", isLeader'" + isLeader + '\'' +
                '}';
    }

}
