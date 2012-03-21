
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
@Table(name="city")
public class City implements Serializable {

    public City()
    {
        
    }

    public City(int locId, String country, String region, String city,
                String postalCode, double latitude, double longitude,
                double metroCode, double areaCode)
    {
        this.setAreaCode(areaCode);
        this.setCity(city);
        this.setCountry(country);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setMetroCode(metroCode);
        this.setPostalCode(postalCode);
        this.setRegion(region);
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;
    private String region;
    private String city;
    private String postalCode;
    private double latitude;
    private double longitude;
    private double metroCode;
    private double areaCode;

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
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blucargo.model.model.City[id=" + id + "]";
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(double metroCode) {
        this.metroCode = metroCode;
    }

    public double getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(double areaCode) {
        this.areaCode = areaCode;
    }

}
