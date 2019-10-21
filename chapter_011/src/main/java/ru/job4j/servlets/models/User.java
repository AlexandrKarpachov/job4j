package ru.job4j.servlets.models;

import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String name;
    private String email;
    private String photoId;
    private Long createDate;
    private Role role;
    private String password;

    public User(Integer id) {
        this.id = id;
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.name = builder.name;
        this.email = builder.email;
        this.photoId = builder.photoId;
        this.createDate = builder.createDate;
        this.role = builder.role;
        this.password = builder.password;
    }

    public static class Builder {
        private Integer id;
        private String login;
        private String name;
        private String email;
        private String photoId;
        private Role role = Role.USER;
        private String password;
        private Long createDate = System.currentTimeMillis();

        public Builder withID(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoto(String photo) {
            this.photoId = photo;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withCreateDate(long date) {
            this.createDate = date;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id)
                && login.equals(user.login)
                && name.equals(user.name)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, email);
    }

    @Override
    public String toString() {
        return String.format("User{id=%s, name=%s, login=%s, email=%s", id, name, login, email);
    }
}
