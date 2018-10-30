
package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class FixupTask extends DomainEntity {

	//relations
	public Actor					author;
	public Category					category;
	public Warranty					warranty;
	public ArrayList<WorkPlanPhase>	phases;
	//attributes
	public String					ticker;
	public Date						publishDate;
	public String					description;
	public String					address;
	public Float					offeredRate;
	public Date						startDate;
	public Date						endDate;
	public boolean					locked;
	public CreditCard				creditCard;


	@NotNull
	@Valid
	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
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

	public Float getOfferedRate() {
		return this.offeredRate;
	}

	public void setOfferedRate(final Float offeredRate) {
		this.offeredRate = offeredRate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	public ArrayList<WorkPlanPhase> getPhases() {
		return this.phases;
	}

	public void setPhases(final ArrayList<WorkPlanPhase> phases) {
		this.phases = phases;
	}

}
