package org.hibernate.bugs.models;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrganizationProductKey implements Serializable {

    @Column(name = "organization_id")
    private String organizationId;

    @Column(name = "product_sku")
    private String productSku;

    public OrganizationProductKey() {
    }

    public OrganizationProductKey(String organizationId, String product_id) {
        this.setOrganizationId(organizationId);
        this.setProductSku(product_id);
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organization_id) {
        this.organizationId = organization_id;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String product_sku) {
        this.productSku = product_sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationProductKey that = (OrganizationProductKey) o;
        return organizationId.equals(that.organizationId) && productSku.equals(that.productSku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, productSku);
    }
}