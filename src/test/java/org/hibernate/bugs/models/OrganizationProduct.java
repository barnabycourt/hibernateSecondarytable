package org.hibernate.bugs.models;

import javax.persistence.*;


@Entity
public class OrganizationProduct {

    @EmbeddedId
    private OrganizationProductKey org_product_id;

    @ManyToOne
    private Product product;

    public OrganizationProduct(OrganizationProductKey org_product_id, Product product) {
        this.org_product_id = org_product_id;
        this.product = product;
    }

    public OrganizationProduct() {

    }

    public OrganizationProductKey getId() {
        return org_product_id;
    }

    public void setId(OrganizationProductKey id) {
        this.org_product_id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}