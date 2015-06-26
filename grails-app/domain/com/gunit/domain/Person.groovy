package com.gunit.domain

class Person {

	transient springSecurityService

	String username
	String password
	String facebookUID
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
//		username blank: false, unique: true
//		password blank: false
		username (validator: { val, obj -> if(obj.facebookUID.trim().length() < 1 && val.trim().length() < 1) return ['username.blank']  }, nullable: true)
		password (validator: { val, obj -> if(obj.facebookUID.trim().length() < 1 && val.trim().length() < 1) return ['password.blank']  }, nullable: true)
	}
	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		PersonRole.findAllByPerson(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
