package com.example.talktou

class User {
    var name:String?=null
    var email:String?=null
    var uid:String?=null

    constructor(){}  // Fire Base needs an empty constructor to work. Otherwise we can remove or don't need a constructor

    constructor(name: String?,email: String?,uid: String?){
        this.name =  name
        this.email = email
        this.uid = uid
    }

}