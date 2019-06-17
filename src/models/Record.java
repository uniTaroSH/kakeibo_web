package models;

import java.sql.Date;
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

@Table(name = "record")
@NamedQueries({
    @NamedQuery(
            name = "getRecords",
            query = "SELECT r FROM Record AS r WHERE r.kakeibo = :kakeibo AND r.delete_flag = 0 ORDER BY r.date ASC"
            ),
    @NamedQuery(
            name = "getTagIncomeSUM",
            query = "SELECT SUM(r.income) FROM Record AS r WHERE r.kakeibo = :kakeibo AND r.tag = :tag AND r.delete_flag = 0"
            ),
    @NamedQuery(
            name = "getTagSpendingSUM",
            query = "SELECT SUM(r.spending) FROM Record AS r WHERE r.kakeibo = :kakeibo AND r.tag = :tag AND r.delete_flag = 0"
            )



})



@Entity
public class Record {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "kakeibo_id")
    private Kakeibo kakeibo;


    @Column(name = "date", nullable = false)
    private Date date;


    @Column(name = "item", nullable = false)
    private String item;


    @Column(name = "tag")
    private String tag;


    @Column(name = "income", nullable = false)
    private Integer income;


    @Column(name = "spending", nullable = false)
    private Integer spending;


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


    public Kakeibo getKakeibo() {
        return kakeibo;
    }


    public void setKakeibo(Kakeibo kakeibo) {
        this.kakeibo = kakeibo;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getItem() {
        return item;
    }


    public void setItem(String item) {
        this.item = item;
    }


    public String getTag() {
        return tag;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }


    public Integer getIncome() {
        return income;
    }


    public void setIncome(Integer income) {
        this.income = income;
    }


    public Integer getSpending() {
        return spending;
    }


    public void setSpending(Integer spending) {
        this.spending = spending;
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
