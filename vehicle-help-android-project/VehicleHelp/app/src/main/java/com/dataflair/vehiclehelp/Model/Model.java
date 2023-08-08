package com.dataflair.vehiclehelp.Model;

public class Model {

    String id,mail,name,profilepic,role,mechanicLocation,mechanicNumber,mechanicPrice,vehicleName,mechanicName;

    public Model() {}

    public Model(String id, String mail, String name, String profilepic, String role, String mechanicLocation, String mechanicNumber, String mechanicPrice, String vehicleName, String mechanicName) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.profilepic = profilepic;
        this.role = role;
        this.mechanicLocation = mechanicLocation;
        this.mechanicNumber = mechanicNumber;
        this.mechanicPrice = mechanicPrice;
        this.vehicleName = vehicleName;
        this.mechanicName = mechanicName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMechanicLocation() {
        return mechanicLocation;
    }

    public void setMechanicLocation(String mechanicLocation) {
        this.mechanicLocation = mechanicLocation;
    }

    public String getMechanicNumber() {
        return mechanicNumber;
    }

    public void setMechanicNumber(String mechanicNumber) {
        this.mechanicNumber = mechanicNumber;
    }

    public String getMechanicPrice() {
        return mechanicPrice;
    }

    public void setMechanicPrice(String mechanicPrice) {
        this.mechanicPrice = mechanicPrice;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }
}
