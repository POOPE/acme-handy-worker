
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class FixupTask extends DomainEntity {

	//relations
	public Customer				author;
	public Warranty				warranty;
	public List<WorkPlanPhase>	phases;
	public Category				category;
	//attributes
	public String				ticker;
	public Date					publishDate;
	public String				description;
	public String				address;
	public Float				maximumPrice;
	public Date					startDate;
	public Date					endDate;
	public boolean				locked;
	public CreditCard			creditCard;


	@ManyToOne(optional = false)
	public Customer getAuthor() {
		return this.author;
	}

	public void setAuthor(final Customer author) {
		this.author = author;
	}

	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(final Date publishDate) {
		this.publishDate = publishDate;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@NotNull
	@Digits(integer = 10, fraction = 2)
	public Float getMaximumPrice() {
		return this.maximumPrice;
	}

	public void setMaximumPrice(final Float maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public boolean isLocked() {
		return this.locked;
	}

	public void setLocked(final boolean locked) {
		this.locked = locked;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@ManyToOne(optional = true)
	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	@OneToMany
	public List<WorkPlanPhase> getPhases() {
		return this.phases;
	}

	public void setPhases(List<WorkPlanPhase> phases) {
		this.phases = phases;
	}

	@ManyToOne(optional = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

}
