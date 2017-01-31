/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs;

import static junit.framework.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openmrs.test.BaseContextSensitiveTest;

public class BaseOpenmrsObjectTest extends BaseContextSensitiveTest {
	
	private static class BaseOpenmrsObjectMock extends BaseOpenmrsObject {
		
		@Override
		public Integer getId() {
			return null;
		}
		
		@Override
		public void setId(Integer id) {
		}
		
	}
	
	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if given obj has null uuid
	 */
	@Test
	public void equals_shouldReturnFalseIfGivenObjHasNullUuid() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		BaseOpenmrsObject obj = new BaseOpenmrsObjectMock();
		
		//when
		obj.setUuid(null);
		
		//then
		Assert.assertFalse(o.equals(obj));
	}
	
	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if given obj is not instance of BaseOpenmrsObject
	 */
	@Test
	public void equals_shouldReturnFalseIfGivenObjIsNotInstanceOfBaseOpenmrsObject() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		//when
		Object obj = new Object();
		
		//then
		Assert.assertFalse(o.equals(obj));
	}
	
	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if given obj is null
	 */
	@Test
	public void equals_shouldReturnFalseIfGivenObjIsNull() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		//when
		BaseOpenmrsObject obj = null;
		
		//then
		Assert.assertFalse(o.equals(obj));
	}
	
	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if uuid is null
	 */
	@Test
	public void equals_shouldReturnFalseIfUuidIsNull() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		BaseOpenmrsObject obj = new BaseOpenmrsObjectMock();
		
		//when
		o.setUuid(null);
		
		//then
		Assert.assertFalse(o.equals(obj));
	}
	
	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return true if objects are the same
	 */
	@Test
	public void equals_shouldReturnTrueIfObjectsAreTheSame() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		//when
		BaseOpenmrsObject obj = o;
		
		//then
		Assert.assertTrue(o.equals(obj));
	}
	
	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return true if uuids are equal
	 */
	@Test
	public void equals_shouldReturnTrueIfUuidsAreEqual() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		BaseOpenmrsObject obj = new BaseOpenmrsObjectMock();
		
		//when
		obj.setUuid(o.getUuid());
		
		//then
		Assert.assertTrue(o.equals(obj));
	}
	
	/**
	 * @see BaseOpenmrsObject#hashCode()
	 * @verifies not fail if uuid is null
	 */
	@Test
	public void hashCode_shouldNotFailIfUuidIsNull() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		//when
		o.setUuid(null);
		
		//then
		o.hashCode();
	}
	
	@Test
	public void shouldNotBeEqualWhenDifferentClassesAndSameId() throws Exception {
		Encounter encounter = new Encounter(2);
		Order order = new Order(2);
		
		Assert.assertFalse(encounter.equals(order));
	}
	
	@Test
	public void shouldNotBeEqualWhenFirstIsNull() throws Exception {
		Encounter encounter = new Encounter(2);
		Assert.assertFalse(encounter.equals(null));
	}
	
	/**
	 * @see BaseOpenmrsObject#toString()
	 * @verifies include uuid if not null
	 */
	@Test
	public void toString_shouldIncludeUuidIfNotNull() throws Exception {
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		assertEquals("BaseOpenmrsObjectTest.BaseOpenmrsObjectMock[hashCode=" + Integer.toHexString(o.hashCode()) + ",uuid="
		        + o.getUuid() + "]", o.toString());
	}
	
	/**
	 * @see BaseOpenmrsObject#toString()
	 * @verifies include hashcode if uuid is null
	 */
	@Test
	public void toString_shouldIncludeHashCodeIfUuidIsNull() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		o.setUuid(null);
		
		//when
		//then
		assertEquals("BaseOpenmrsObjectTest.BaseOpenmrsObjectMock[hashCode=" + Integer.toHexString(o.hashCode())
		        + ",uuid=<null>]", o.toString());
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return true if given object is instance of super or extending class
	 */
	@Test
	public void equals_shouldReturnTrueIfGivenObjectIsInstanceOfSuperOrExtendingClass() throws Exception {
		Concept concept = new Concept(5);
		Concept numeric = new ConceptNumeric();
		numeric.setUuid(concept.getUuid());
		Assert.assertTrue(numeric.equals(concept));
		Assert.assertTrue(concept.equals(numeric));
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if given object is not instance of super or extending class
	 */
	@Test
	public void equals_shouldReturnFalseIfGivenObjIsNotInstanceOfSuperOrExtendingClass() throws Exception {
		Encounter encounter = new Encounter();
		Concept concept = new Concept(5);
		concept.setUuid(encounter.getUuid());
		Assert.assertFalse(encounter.equals(concept));
		Assert.assertFalse(concept.equals(encounter));
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return true if given object is subclass of super or extending class
	 */
	@Test
	public void equals_shouldReturnTrueIfGivenObjectIsSubclassOfSuperOrExtendingClass() throws Exception {
		Order order = new Order(21);
		DrugOrder type = new DrugOrder(21);
		type.setUuid(order.getUuid());
		Assert.assertTrue(type.equals(order));
		Assert.assertTrue(order.equals(type));
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if given object is not subclass of super or extending class
	 */
	@Test
	public void equals_shouldReturnFalseIfGivenObjectIsNotSubclassOfSuperOrExtendingClass() throws Exception {
		Order order = new Order(21);
		OrderFrequency type = new OrderFrequency();
		type.setUuid(order.getUuid());
		Assert.assertFalse(type.equals(order));
		Assert.assertFalse(order.equals(type));
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if HibernateProxy of one thing is compared to HibernateProxy of something
	 */
	@Test
	public void equals_shouldReturnfalseIfHibernateProxyOfOneThingIsComparedtoHibernateProxyofSomething() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		Assert.assertFalse((session.load(Patient.class, 2)).equals((session.load(Concept.class, 11))));
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies return false if HibernateProxy of one thing is compared to Non-HibernateProxy of something
	 */
	@Test
	public void equals_shouldReturnFalseIfHibernateProxyOfOneThingIsComparedtoNonHibernateProxyofSomething() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();

		//NonHibernate managed class declaration
		class TestClass extends BaseOpenmrsObject {
			private Integer id;
			TestClass() {
			}
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public int hashCode() {
				if (getUuid() == null) {
					return super.hashCode();
				}
				return getUuid().hashCode();
			}
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (!(obj instanceof TestClass)) {
					return false;
				}
				TestClass other = (TestClass) obj;
				if (getUuid() == null) {
					return false;
				}
				return getUuid().equals(other.getUuid());
			}
			public String toString() {
				return new org.apache.commons.lang3.builder.ToStringBuilder(this, org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE).append("hashCode",
						Integer.toHexString(hashCode())).append("uuid", getUuid()).build();
			}
		}

		Patient patient = (Patient) session.get(Patient.class, 2);
		String uid = patient.getUuid();

		//NonHibernate managed class Instantiation
		TestClass obj = new TestClass();
		obj.setUuid(uid);

		Assert.assertFalse(obj.equals((session.load(Patient.class, 2))));
		Assert.assertFalse((session.load(Patient.class, 2)).equals(obj));
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies should work on NonHibernate managed classes
	 */
	@Test
	public void equals_shouldWorkOnNonHibernateManagedClasses() throws Exception {
		//NonHibernate managed class
		class TestClass extends BaseOpenmrsObject {
			private Integer id;
			TestClass() {
			}
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public int hashCode() {
				if (getUuid() == null) {
					return super.hashCode();
				}
				return getUuid().hashCode();
			}
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (!(obj instanceof TestClass)) {
					return false;
				}
				TestClass other = (TestClass) obj;
				if (getUuid() == null) {
					return false;
				}
				else {
					return getUuid().equals(other.getUuid());
				}
			}
			public String toString() {
				return new org.apache.commons.lang3.builder.ToStringBuilder(this, org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE).append("hashCode",
						Integer.toHexString(hashCode())).append("uuid", getUuid()).build();
			}
		}

		//Another NonHibernate managed class
		class AnotherTestClass extends BaseOpenmrsObject {
			private int id;
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (!(obj instanceof AnotherTestClass)) {
					return false;
				}
				AnotherTestClass other = (AnotherTestClass) obj;
				if (getUuid() == null) {
					return false;
				}
				return getUuid().equals(other.getUuid());
			}

			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}
		}

		//Object of a NonHibernate managed class
		TestClass testObj = new TestClass();
		String uuid = testObj.getUuid();

		TestClass testObjsameuuid = new TestClass();
		testObjsameuuid.setUuid(uuid);
		//Object of Another NonHibernate managed class
		AnotherTestClass anotherTestObj = new AnotherTestClass();
		anotherTestObj.setUuid(uuid);

		AnotherTestClass anotherTestObjectsameUuid = new AnotherTestClass();
		anotherTestObjectsameUuid.setUuid(uuid);

		Assert.assertFalse(anotherTestObj.equals(testObj));
		Assert.assertFalse(testObj.equals(anotherTestObj));
		Assert.assertTrue(testObj.equals(testObjsameuuid));
		Assert.assertTrue(anotherTestObj.equals(anotherTestObjectsameUuid));
		Assert.assertTrue(testObjsameuuid.equals(testObj));
		Assert.assertTrue(anotherTestObjectsameUuid.equals(anotherTestObj));

	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies should return true if the HibernateProxy of one object is compared to NonHibernateProxy of the same object
	 */
	@Test
	public void equals_shouldReturnTrueIfHibernateProxyOfOneObjectComparedToNonHibernateProxyOfTheSameObject(){
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();

		Patient patient = (Patient) session.get(Patient.class, 2);
		Patient patientproxyobject = (Patient) session.load(Patient.class, 2);

		Assert.assertTrue(patient.equals(patientproxyobject));
	}

	/**
	 * @see BaseOpenmrsObject#equals(Object)
	 * @verifies should return true if HibernateProxy of some object compared to AnotherHibernateProxy of The same object
	 */
	@Test
	public void equals_shouldReturnTrueIfHibernateProxyOfSomeObjectComparedToAnotherHibernateProxyOfTheSameObject(){
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();

		Assert.assertTrue(session.load(Patient.class, 2).equals((session.load(Patient.class, 2)))) ;
	}
}