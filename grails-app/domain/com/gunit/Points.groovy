package com.gunit

class Points {
	int generation = 1
	float discount
	long value
	String note
	
	static hasMany = [statement: Statement]
	static belongsTo = Statement

    static constraints = {
		generation nullable: false, min: 1, max: 6 
		discount blank: true, min: 0F, max: 30F
		value blank: false, unique: true, min: 1L
		note blank: true, maxSize: 1000
    }
	
	String toString() {
		return "Generation " + generation + ": " + value + " Points"
	}
}
