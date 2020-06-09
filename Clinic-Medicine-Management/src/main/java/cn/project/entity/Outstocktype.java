package cn.project.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Outstocktype {
    private long id;
    private String typename;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TYPENAME", nullable = false, length = 50)
    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outstocktype that = (Outstocktype) o;
        return id == that.id &&
                Objects.equals(typename, that.typename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typename);
    }
}
