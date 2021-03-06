package org.dayatang.hrm.organisation.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends Party {

	private static final long serialVersionUID = -7339118476080239701L;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	Employee() {
	}

	public Employee(String name) {
		super(name);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Set<Post> getPosts(Date date) {
		return new HashSet<Post>(PostHolding.findPostsOfEmployee(this, date));
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Employee)) {
			return false;
		}
		Employee that = (Employee) other;
		return new EqualsBuilder().append(this.getSn(), that.getSn())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getSn()).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(getName()).build();
	}

}
