package oop.io.demo.analysis.payload;

import javax.validation.constraints.NotBlank;

public class AnalysisRequest {
	@NotBlank
	private String year;
	private String month;

	public AnalysisRequest(@NotBlank String year, @NotBlank String month) {
		this.year = year;
		this.month = month;
	}

	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

}

