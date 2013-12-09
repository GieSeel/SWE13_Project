package de.dhbw.swe.camping_site_mgt.common;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Duration extends BaseDataObject {
	public Duration() {
		this(null, null);
	}
	
	public Duration(Date from, Date until) {
		this(0, from, until);
	}
	
	public Duration(int id, Date from, Date until) {
		super(id);
		this.from = from;
		this.until = until;
	}
	
	public Date getFrom() {
		return from;
	}
	
	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getUntil() {
		return until;
	}
	
	public void setUntil(Date until) {
		this.until = until;
	}
	
	public Integer getElapsedDays() {
		// TODO
		return 0;
	}
	
	private Date from;
	private Date until;
	
	@Override
	public boolean equals(DataObject dataObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTableName() {
		return "duration";
	}
}
