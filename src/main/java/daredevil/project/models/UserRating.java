package daredevil.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_ratings")
public class UserRating {
    public UserRating() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_rating", nullable = false)
    private int rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ratedID", nullable = false)
    @JsonIgnore
    private User users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "raterID", nullable = false)
    @JsonIgnore
    private User rater;



    public UserRating(int rating, User users) {
        this.rating = rating;
        this.users = users;
    }

    public UserRating(int rating, User users, User rater) {
        this.rating = rating;
        this.users = users;
        this.rater = rater;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUsers() {
        return users;
    }

    public User getRater() {
        return rater;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }

    public void setUsers(User users) {
        this.users = users;

    }
}
