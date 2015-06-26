package br.com.alinesolutions.marthalanches.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7580293782384613788L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return Boolean.TRUE;
		} else {
			if (obj == null) {
				return Boolean.FALSE;
			} else if (getClass() != obj.getClass()) {
				return Boolean.FALSE;
			}
			BaseEntity other = (BaseEntity) obj;
			if (id == null) {
				if (other.id != null) {
					return Boolean.FALSE;
				}
			} else if (!id.equals(other.id)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
}
