package com.example.guru.task2;

import java.io.Serializable;

/**
 * Created by User on 20-02-2018.
 */

public class UserDetails implements Serializable{

    private String rollNo, name, address, genderIs;


    UserDetails(String Address, String Name, String Gender, String RollNo) {
        this.address = Address;
        this.name = Name;
        this.genderIs = Gender;
        this.rollNo = RollNo;

    }


    public void setName(String Name) {

        this.name = Name;

    }

    public void setAddress(String Address) {

        this.address = Address;

    }

    public void setRollNo(String Roll) {

        this.rollNo = Roll;

    }

    public void setGenderIs(String GenderIs)
    {
        this.genderIs = GenderIs;
    }


    public String getName() {

        return name;

    }

    public String getRollNo() {

        return rollNo;

    }

    public String getAddress() {

        return address;

    }

    public String getGenderIs() {

        return genderIs;

    }

    @Override
    public boolean equals(Object obj) {
        return this.rollNo.equalsIgnoreCase(((UserDetails) obj).getRollNo());
    }
}
