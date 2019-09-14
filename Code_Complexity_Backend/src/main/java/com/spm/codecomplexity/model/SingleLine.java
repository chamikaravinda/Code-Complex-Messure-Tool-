package com.spm.codecomplexity.model;

public class SingleLine {

	private int lineNumber;
	private String statement;
	private int cs;
	private int ctc;
	private int cnc;
	private int ci;
	private int tw;
	private int cps;
	private int cr;
	private String tokensOnStatmentSize="";


	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public int getCtc() {
		return ctc;
	}

	public void setCtc(int ctc) {
		this.ctc = ctc;
	}

	public int getCnc() {
		return cnc;
	}

	public void setCnc(int cnc) {
		this.cnc = cnc;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

	public int getTw() {
		return tw;
	}

	public void setTw(int tw) {
		this.tw = tw;
	}

	public int getCps() {
		return cps;
	}

	public void setCps(int cps) {
		this.cps = cps;
	}

	public int getCr() {
		return cr;
	}

	public void setCr(int cr) {
		this.cr = cr;
	}

	@Override
	public String toString() {
		return "ControlStructureType [statement=" + statement + ", cs=" + cs + ", ctc=" + ctc + ", cnc=" + cnc + ", ci="
				+ ci + ", tw=" + tw + ", cps=" + cps + ", cr=" + cr + "]";
	}
  
	public String getTokensOnStatmentSize() {
		return tokensOnStatmentSize;
	}

	public void setTokensOnStatmentSize(String tokensOnStatmentSize) {
		this.tokensOnStatmentSize =this.tokensOnStatmentSize+" | " + tokensOnStatmentSize;
	}

}
