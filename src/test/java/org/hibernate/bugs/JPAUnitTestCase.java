package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.bugs.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;
	private Logger log;

	@Before
	public void init() {
		log = Logger.getLogger(JPAUnitTestCase.class.getName());
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Test & demonstrate linkage to OrgProduct
	@Test
	public void orgProductTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		// Build the products
		Product prod1 = new Product("SKU01", "Product Foo");
		Product prod2 = new Product("SKU01", "Product Foo Changed");
		entityManager.persist(prod1);
		entityManager.persist(prod2);

		// Build the Organization
		Organization org1 = new Organization("ORG1", "Acme Inc");
		entityManager.persist(org1);

		// Build the OrgProduct Record
		OrganizationProduct org_product = new OrganizationProduct(new OrganizationProductKey(org1.getId(), "SKU01"), prod1);
		entityManager.persist(org_product);

		// Create a Subscription
		Subscription s = new Subscription("Foo Sub", org1, org_product);
		entityManager.persist(s);


		// Lets start querying to make sure the initial product gets pulled properly
		Subscription queriedSub = entityManager.find(Subscription.class, s.getId());
		Assert.assertEquals(prod1.getName(), queriedSub.getOrgProduct().getProduct().getName());

		// Check that we can update the product record for the SKU & it matches prod2
		org_product.setProduct(prod2);
		queriedSub = entityManager.find(Subscription.class, s.getId());
		Assert.assertEquals(prod2.getName(), queriedSub.getOrgProduct().getProduct().getName());

		// TODO
		// What I would like to be able to do is reference product directly from Subscription i.e.
		// qeriedSub.getProduct().getName();
		// This avoids having to bounce through getOrgProduct() every time.

		// Rollback everything so that tests don't have side effects
		entityManager.getTransaction().rollback();
		entityManager.close();
	}
}
