package com.example.artroo;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class Operation {

	private BigDecimal amount;
	private BigDecimal debited;
	private BigDecimal credited;
	private  Date date;
	private String through;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the debited
	 */
	public BigDecimal getDebited() {
		return debited;
	}
	/**
	 * @param debited the debited to set
	 */
	public void setDebited(BigDecimal debited) {
		this.debited = debited;
	}
	/**
	 * @return the credited
	 */
	public BigDecimal getCredited() {
		return credited;
	}
	/**
	 * @param credited the credited to set
	 */
	public void setCredited(BigDecimal credited) {
		this.credited = credited;
	}
	/**
	 * @return the through
	 */
	public String getBankName() {
		return through;
	}
	/**
	 * @param through the through to set
	 */
	public void setBankName(String through) {
		this.through = through;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}





}