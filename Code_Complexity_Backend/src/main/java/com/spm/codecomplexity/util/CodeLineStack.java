package com.spm.codecomplexity.util;

import java.util.ArrayList;
import java.util.EmptyStackException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spm.codecomplexity.model.SingleLine;

@Component
public class CodeLineStack {

	private ArrayList<SingleLine> stack;

	public CodeLineStack(ArrayList<SingleLine> stack) {
		super();
		this.stack = stack;
	}

	public void push(SingleLine line){
		stack.add(line);
	}
	
	public SingleLine pop() {
		if(!stack.isEmpty())
			return stack.remove(size()-1);
		else
			throw new EmptyStackException();
	}
	
	public SingleLine peek() {
		if(!stack.isEmpty())
			return stack.get(size()-1);
		else
			throw new EmptyStackException();
	}
	
	public int size() {
		return stack.size();
	}

	@Override
	public String toString() {
		return "CodeLineStack [stack=" + stack + "]";
	}
	
	
}
