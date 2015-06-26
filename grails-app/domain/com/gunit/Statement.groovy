package com.gunit

import java.util.Date;

class Statement {
	String id
	String branchNo
	Date createdDate
	Date updatedDate
	String lastUpdatedBy

	static hasMany = [points: Points]

    static constraints = {
		id blank: false, unique: true
		branchNo blank: false
		createdDate nullable: false
		updatedDate nullable: false
		lastUpdatedBy blank: false
		points nullable: false
    }
}
