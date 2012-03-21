
package com.blucargo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="country")
public class Country implements Serializable {

    public Country()
    {
        
    }

    public Country(String iso_3166_alfa_2, String name_big, String name, String iso_3166_1_alfa_3, String iso_3166_1_digital)
    {
        this.setIso_3166_1_alfa_2(iso_3166_alfa_2);
        this.setName_big(name_big);
        this.setName(name);
        this.setIso_3166_1_alfa_3(iso_3166_1_alfa_3);
        this.setIso_3166_1_digital(iso_3166_1_digital);
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String iso_3166_1_alfa_2;
    private String name_big;
    private String name;
    private String iso_3166_1_alfa_3;
    private String iso_3166_1_digital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blucargo.model.Country[id=" + id + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_3166_1_alfa_2() {
        return iso_3166_1_alfa_2;
    }

    public void setIso_3166_1_alfa_2(String iso_3166_1_alfa_2) {
        this.iso_3166_1_alfa_2 = iso_3166_1_alfa_2;
    }

    public String getName_big() {
        return name_big;
    }

    public void setName_big(String name_big) {
        this.name_big = name_big;
    }

    public String getIso_3166_1_alfa_3() {
        return iso_3166_1_alfa_3;
    }

    public void setIso_3166_1_alfa_3(String iso_3166_1_alfa_3) {
        this.iso_3166_1_alfa_3 = iso_3166_1_alfa_3;
    }

    public String getIso_3166_1_digital() {
        return iso_3166_1_digital;
    }

    public void setIso_3166_1_digital(String iso_3166_1_digital) {
        this.iso_3166_1_digital = iso_3166_1_digital;
    }

}
