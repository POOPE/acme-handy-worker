
package domain;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class Finder extends DomainEntity {

	public Date				creationDate;

	public String			keyWord;

	public String			category;

	public String			warranty;

	public Double			minPrice;

	public Double			maxPrice;

	public Date				minDate;

	public Date				maxDate;

	public HandyWorker		handyWorker;

	Collection<FixupTask>	fixUpTasks;


	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final String warranty) {
		this.warranty = warranty;
	}

	public Double getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(final Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(final Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMinDate() {
		return this.minDate;
	}

	public void setMinDate(final Date minDate) {
		this.minDate = minDate;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(final Date maxDate) {
		this.maxDate = maxDate;
	}

	public HandyWorker getHandyWorker() {
		return this.handyWorker;
	}

	public void setHandyWorker(final HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

	public Collection<FixupTask> getFixUpTasks() {
		return this.fixUpTasks;
	}

	public void setFixUpTasks(final Collection<FixupTask> fixUpTasks) {
		this.fixUpTasks = fixUpTasks;
	}

}