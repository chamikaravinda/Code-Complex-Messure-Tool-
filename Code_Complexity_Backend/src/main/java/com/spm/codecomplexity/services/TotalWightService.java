package com.spm.codecomplexity.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spm.codecomplexity.model.SingleLine;

import java.util.ArrayList;
import java.util.List;

@Service
public class TotalWightService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public List<SingleLine> calculateTotalWeight(List<SingleLine> statmentList){ 
		LOG.info("DATA Recived for the Total Weight Calculation Service Method");

		for(SingleLine line :statmentList) {
			line.setTw(line.getCtc()+line.getCnc()+line.getCi());
		}
		
		return statmentList;
	}
	
}
