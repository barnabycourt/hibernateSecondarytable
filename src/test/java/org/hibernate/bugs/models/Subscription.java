package org.hibernate.bugs.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
//@SecondaryTable(name="OrganizationProduct", pkJoinColumns={
//        @PrimaryKeyJoinColumn(name="organization_id", referencedColumnName="organization_id"),
//        @PrimaryKeyJoinColumn(name="product_sku", referencedColumnName="product_sku")
//})
public class Subscription implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    @Id
//    @Column(name = "product_sku")
//    private String sku;

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;

    // JoinTable errors with "org.hibernate.AnnotationException: SecondaryTable JoinColumn cannot reference a non primary key"
    /*
    @ManyToOne
    @JoinTable(
            name = "OrganizationProduct",
            joinColumns = {
                    @JoinColumn(table = "Subscription", name = "product_sku", referencedColumnName = "product_sku"),
                    @JoinColumn(table = "Subscription", name = "organization_id", referencedColumnName = "organization_id")

            },
            inverseJoinColumns = {
                    @JoinColumn(table = "Product", name = "product_id", referencedColumnName = "id")
            }
    )

    private Product product;
     */

    @ManyToOne
    @JoinColumn(name = "organization_id", insertable = false, updatable = false)
    @JoinColumn(name = "product_sku", insertable = false, updatable = false)
    private OrganizationProduct org_product;

//    @ManyToOne
//    @JoinColumn(table = "organization_product", name = "product_id", insertable = false, updatable = false)
//    private Product product;

    public Subscription() {
    }

    public Subscription(String name, Organization org, OrganizationProduct org_product) {
        this.organization = org;
        this.name = name;
        this.org_product = org_product;
//        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
*/
    public OrganizationProduct getOrgProduct() {
        return org_product;
    }

    public void setOrgProduct(OrganizationProduct org_product) {
        this.org_product = org_product;
    }

}