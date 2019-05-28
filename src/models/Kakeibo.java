package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name = "kakeibo")
@NamedQueries({
    @NamedQuery(
            name = "getMyAllKakeibo",
            query = "SELECT k FROM Kakeibo AS k WHERE k.user = :user AND k.delete_flag = 0 ORDER BY k.id DESC"
            ),
    @NamedQuery(
            name = "getMyKakeiboCount",
            query = "SELECT COUNT(k) FROM Kakeibo AS k WHERE k.user = :user AND k.delete_flag = 0"
            ),

})


@Entity
public class Kakeibo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(name = "pageName", nullable = false)
    private String pageName;


    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


    @Column(name = "created_at", nullable =false)
    private Timestamp created_at;


    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public String getPageName() {
        return pageName;
    }


    public void setPageName(String pageName) {
        this.pageName = pageName;
    }


    public Integer getDelete_flag() {
        return delete_flag;
    }


    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }


    public Timestamp getCreated_at() {
        return created_at;
    }


    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }


    public Timestamp getUpdated_at() {
        return updated_at;
    }


    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
