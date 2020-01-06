package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */

@Entity
public class History implements Serializable {
//private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    private Product product;
    @OneToOne()
    private Buyer buyer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date takeOn;

    public History() {
    }

    public History(Long id, Product product, Buyer buyer, Date takeOn) {
        this.id = id;
        this.product = product;
        this.buyer = buyer;
        this.takeOn = takeOn;
    }

    @Override
    public String toString() {
        return "History{" + "product=" + product + ", buyer=" + buyer + ", takeOn=" + takeOn + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.product);
        hash = 97 * hash + Objects.hashCode(this.buyer);
        hash = 97 * hash + Objects.hashCode(this.takeOn);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        if (!Objects.equals(this.takeOn, other.takeOn)) {
            return false;
        }

        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Date getTakeOn() {
        return takeOn;
    }

    public void setTakeOn(Date takeOn) {
        this.takeOn = takeOn;
    }

}